package br.com.fioalpha.test

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.reflect.KClass

fun String.getFileMock(file: ClassLoader?) = file?.getResource(this)?.readText().orEmpty()


inline fun <reified T> String.convertTo(): T {
    return GsonBuilder().create().fromJson(this, object : TypeToken<T>() {}.type )
}