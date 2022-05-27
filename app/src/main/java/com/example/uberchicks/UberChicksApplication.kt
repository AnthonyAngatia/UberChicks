package com.example.uberchicks

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
* This class is a container that is attached to the app's life cycle
* Annotating it with @HiltAndroidApp generates some code that can be used by the application.
*  The application container is the parent container for the app, which means that other containers
* can access the dependencies that it provides. */
@HiltAndroidApp
class UberChicksApplication: Application() {
}