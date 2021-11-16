package com.mohamed.overplay.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.overplay.usecase.GetSessionCountUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
	private val getSessionCountUseCase: GetSessionCountUseCase): ViewModel() {

	val sessionCount = MutableLiveData<Int>()

	fun getSessionCount() {
		viewModelScope.launch {
			sessionCount.postValue(getSessionCountUseCase.getSessionCount())
		}
	}
}