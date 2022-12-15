package com.android.phonepemcround.data.repo

import com.android.phonepemcround.data.source.local.CompanyDataLocalSource
import javax.inject.Inject

class CompanyDataRepo @Inject constructor(private val localSource: CompanyDataLocalSource) {

    fun getData()= localSource.localData
}