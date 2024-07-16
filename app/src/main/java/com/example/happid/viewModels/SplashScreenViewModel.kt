// SplashScreenViewModel.kt
package com.example.happid.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean> = _navigateToMain

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            delay(2000) // 3 seconds
            _navigateToMain.value = true
        }
    }
}
