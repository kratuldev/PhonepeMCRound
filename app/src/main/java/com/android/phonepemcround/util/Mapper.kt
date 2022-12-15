package com.android.phonepemcround.util

import com.android.phonepemcround.data.model.CompanyInfo
import com.android.phonepemcround.ui.UiData

fun CompanyInfo.toUiDat(randomised: String) = UiData(
    imageSource = this.imgSource,
    randomizedString = randomised,
    actualName = this.name
)