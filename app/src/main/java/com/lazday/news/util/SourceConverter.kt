package com.lazday.news.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lazday.news.data.news.SourceModel

/**
 * Fungsi dari SourceConverter ini adalah:
 *      toSource => digunakan untuk merubah json ke String
 *      fromSource => digunakan untuk merubah String ke json
 * Contoh:
 *      json ke string : {id:1, name:"andika"} => "{id:1, name:"andika"}"
 *      string ke json : "{id:1, name:"andika"}" => {id:1, name:"andika"}
 */
object SourceConverter {
    @TypeConverter
    @JvmStatic
    fun toSource(value: String?): SourceModel {
        val modelType = object : TypeToken<SourceModel>() {}.type
        return Gson().fromJson(value, modelType)
    }

    @TypeConverter
    @JvmStatic
    fun fromSource(source: SourceModel?): String {
        val gson = Gson()
        return gson.toJson(source)
    }
}