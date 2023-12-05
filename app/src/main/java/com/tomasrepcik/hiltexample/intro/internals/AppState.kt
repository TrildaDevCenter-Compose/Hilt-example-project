package com.tomasrepcik.hiltexample.app.intro.internals

sealed class AppState {

    object NotOnboarded: AppState()
    object Onboarded: AppState()

}