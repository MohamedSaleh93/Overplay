package com.mohamed.overplay

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	lateinit var sensorManager: SensorManager
	var sensor: Sensor? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

		if (sensor == null) {
			Toast.makeText(this, R.string.gyroscope_sensor_not_found, Toast.LENGTH_LONG).show()
		} else {
			registerGyroscopeSensorListener()
		}
	}

	private fun registerGyroscopeSensorListener() {
		val gyroscopeSensorListener = object : SensorEventListener {
			override fun onSensorChanged(sensorEvent: SensorEvent?) {
				val rotationMatrix = FloatArray(16)
				SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent?.values)
				val remappedRotationMatrix = FloatArray(16)
				SensorManager.remapCoordinateSystem(
					rotationMatrix,
					SensorManager.AXIS_X,
					SensorManager.AXIS_Z,
					remappedRotationMatrix)
				val orientations = FloatArray(3)
				SensorManager.getOrientation(remappedRotationMatrix, orientations)
				for (i in 0 .. 2) {
					orientations[i] = Math.toDegrees(orientations[i].toDouble()).toFloat()
				}

				if (orientations[2] <= 30 && orientations[2] >= -30) {
					// Nothing changed
						Log.d("TAG", "No rotation")
					sessionCountText.textSize = resources.getDimension(R.dimen.default_text_size)
				} else if (orientations[2] > 30) {
					// Rotated 30 degree to the right
					Log.d("TAG", "Rotation to right")
					sessionCountText.textSize = resources.getDimension(R.dimen.rotate_right_text_size)
				} else if (orientations[2] < -30) {
					// Rotated 30 degree to the left
					Log.d("TAG", "Rotation to left")
					sessionCountText.textSize = resources.getDimension(R.dimen.rotate_left_text_size)
				}
			}

			override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

			}
		}
		sensorManager.registerListener(gyroscopeSensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
	}

}