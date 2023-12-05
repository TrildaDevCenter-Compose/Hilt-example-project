package com.tomasrepcik.hiltexample.app.intro.internals

import androidx.lifecycle.ViewModel
import com.tomasrepcik.hiltexample.intro.INTRO_VERSION
import com.tomasrepcik.hiltexample.intro.repo.MailClient
import com.tomasrepcik.hiltexample.intro.repo.SettingsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(
    private val settingsRepo: SettingsRepo, private val mailClient: MailClient
) : ViewModel() {

    private val _isOnboarded: MutableStateFlow<AppState> = MutableStateFlow(AppState.NotOnboarded)
    var isOnboarded = _isOnboarded.asStateFlow()

    init {
        _isOnboarded.value =
            if (settingsRepo.isOnboarded() == INTRO_VERSION)
                AppState.Onboarded
            else AppState.NotOnboarded
    }

    fun onEvent(appEvent: AppEvent) = when (appEvent) {
        AppEvent.FinishOnboarding -> saveUserOnboarding()
        AppEvent.SendMail -> sendMail()
    }

    private fun saveUserOnboarding() {
        _isOnboarded.value = AppState.Onboarded
        settingsRepo.saveOnboardingState(INTRO_VERSION)
    }

    private fun sendMail() = mailClient.sendMail()
}