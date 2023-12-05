package com.tomasrepcik.hiltexample.app.intro.internals

sealed class AppEvent {

    object FinishOnboarding: AppEvent()
    object SendMail: AppEvent()

}