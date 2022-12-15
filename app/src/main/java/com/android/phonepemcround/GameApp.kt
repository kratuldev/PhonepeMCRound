package com.android.phonepemcround

import android.app.Application
import com.android.phonepemcround.di.DaggerAppComponent

class GameApp : Application() {

    val appComponent = DaggerAppComponent.factory().create(this.applicationContext, this)
}