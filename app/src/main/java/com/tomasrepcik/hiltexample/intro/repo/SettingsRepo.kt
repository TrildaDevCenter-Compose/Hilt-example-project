package com.tomasrepcik.hiltexample.intro.repo

interface SettingsRepo {

    fun isOnboarded(): Int
    fun saveOnboardingState(isOnboarded: Int)
}