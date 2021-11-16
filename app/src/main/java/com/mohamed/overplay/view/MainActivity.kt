package com.mohamed.overplay.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mohamed.overplay.OverplayApp
import com.mohamed.overplay.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

	@Inject lateinit var viewModelFactory: MainViewModelFactory
	private lateinit var mainViewModel: MainViewModel
	lateinit var sensorManager: SensorManager
	var sensor: Sensor? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		OverplayApp.appComponent.inject(this)
		setContentView(R.layout.activity_main)
		initializeSensor()
		initializeViewModelAndObservers()
	}

	private fun initializeSensor() {
		sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
		if (sensor == null) {
			Toast.makeText(this, R.string.gyroscope_sensor_not_found, Toast.LENGTH_LONG).show()
		} else {
			registerGyroscopeSensorListener()
		}
	}

	private fun initializeViewModelAndObservers() {
		mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
		mainViewModel.sessionCount.observe(this, {
			sessionCountText.text = getString(R.string.session_count, it)
		})
	}

	override fun onStart() {
		super.onStart()
		mainViewModel.getSessionCount()
	}

	private fun registerGyroscopeSensorListener() {
		val gyroscopeSensorListener = object : SensorEventListener {
			override fun onSensorChanged(sensorEvent: SensorEvent?) {
				changeTextDependsOnTheAngle(sensorEvent)
			}

			override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

			}
		}
		sensorManager.registerListener(gyroscopeSensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
	}

	private fun changeTextDependsOnTheAngle(sensorEvent: SensorEvent?) {
		val rotationAngle = getRotationAngle(sensorEvent)

		if (rotationAngle <= 30 && rotationAngle >= -30) {
			// Nothing changed
			sessionCountText.textSize = resources.getDimension(R.dimen.default_text_size)
		} else if (rotationAngle > 30) {
			// Rotated 30 degree to the right
			sessionCountText.textSize = resources.getDimension(R.dimen.rotate_right_text_size)
		} else if (rotationAngle < -30) {
			// Rotated 30 degree to the left
			sessionCountText.textSize = resources.getDimension(R.dimen.rotate_left_text_size)
		}
	}

	private fun getRotationAngle(sensorEvent: SensorEvent?): Float {
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
		return orientations[2]
	}

}