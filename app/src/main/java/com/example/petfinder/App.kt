package com.example.petfinder

import android.app.Application
import com.example.petfinder.di.AppDependencies

class App : Application() {

    val appDependencies by lazy {
        AppDependencies(this)
    }

}