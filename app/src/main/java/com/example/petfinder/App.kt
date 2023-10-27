package com.example.petfinder

import android.app.Application

class App : Application() {

    val appDependencies by lazy {
        AppDependencies()
    }

}