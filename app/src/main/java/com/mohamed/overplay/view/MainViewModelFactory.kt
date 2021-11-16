package com.mohamed.overplay.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamed.overplay.usecase.GetSessionCountUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
	private var getSessionCountUseCase: GetSessionCountUseCase
): ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return MainViewModel(getSessionCountUseCase) as T
	}
}