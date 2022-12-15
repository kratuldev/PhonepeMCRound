package com.android.phonepemcround.di

import android.content.Context
import com.android.phonepemcround.GameApp
import dagger.BindsInstance
import dagger.Component

@Component(modules = [PlayerActivityModule::class, ViewModelBuilderModule::class])
interface AppComponent {


    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
            @BindsInstance
            app: GameApp
        ): AppComponent
    }
}