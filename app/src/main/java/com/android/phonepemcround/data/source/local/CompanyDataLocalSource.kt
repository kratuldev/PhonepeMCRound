package com.android.phonepemcround.data.source.local

import android.content.Context
import com.android.phonepemcround.R
import com.android.phonepemcround.data.model.CompanyInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.*
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


class CompanyDataLocalSource
@Inject constructor(
    private val context: Context,
    dispatcher: CoroutineDispatcher,
    private val moshi: Moshi
) {
    val localData: Flow<List<CompanyInfo>?> = flow {
        emit(getCompanyInfo())
    }.flowOn(dispatcher)

    private fun getCompanyInfo(): List<CompanyInfo>? {
        val data: ParameterizedType =
            Types.newParameterizedType(List::class.java, CompanyInfo::class.java)
        val adapter: JsonAdapter<List<CompanyInfo>> = moshi.adapter(data)
        return adapter.fromJson(getCompanyInfoString())
    }

    private fun getCompanyInfoString(): String {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.text)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { `is` ->
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        return writer.toString()
    }
}