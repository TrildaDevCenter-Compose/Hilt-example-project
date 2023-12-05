package com.tomasrepcik.hiltexample.intro.repo

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class SettingsRepoImp @Inject constructor(private val sharedPreferences: SharedPreferences) : SettingsRepo {

    override fun isOnboarded(): Int {
        return sharedPreferences.getInt(PREF_KEY, 0)
    }

    override fun saveOnboardingState(isOnboarded: Int) {
        sharedPreferences.edit().putInt(PREF_KEY, isOnboarded).apply()
        Log.i(TAG, "saveOnboardingState: $isOnboarded")
    }

    companion object {
        const val TAG = "SettingsRepoImp"
        const val PREF_KEY = "onboarding_state"
    }
}