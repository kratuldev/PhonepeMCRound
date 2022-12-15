package com.android.phonepemcround.di

import dagger.Subcomponent

@Subcomponent(modules = [PlayerActivityModule::class])
interface PlayerSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PlayerSubComponent
    }
}