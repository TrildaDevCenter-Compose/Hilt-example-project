package com.tomasrepcik.hiltexample.intro

import android.content.Context
import android.content.SharedPreferences
import com.tomasrepcik.hiltexample.intro.repo.MailClient
import com.tomasrepcik.hiltexample.intro.repo.MailClientImp
import com.tomasrepcik.hiltexample.intro.repo.SettingsRepo
import com.tomasrepcik.hiltexample.intro.repo.SettingsRepoImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingsDi {

    @Binds
    abstract fun provideAppSettings(appSettings: SettingsRepoImp): SettingsRepo

}

@Module
@InstallIn(ViewModelComponent::class)
object MailDi {
    @Provides
    fun provideEmailClient(@ApplicationContext context: Context): MailClient =
        MailClientImp(context)

}


@Module
@InstallIn(SingletonComponent::class)
object IntroAppModules {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("your_prefs_name", Context.MODE_PRIVATE)
    }
}