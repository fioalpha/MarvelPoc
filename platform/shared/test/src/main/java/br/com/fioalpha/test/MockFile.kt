package br.com.fioalpha.test

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

fun String.getFileMock(file: ClassLoader?) = file?.getResource(this)?.readText().orEmpty()

inline fun <reified T> String.convertTo(): T {
    return GsonBuilder().create().fromJson(this, object : TypeToken<T>() {}.type )
}
