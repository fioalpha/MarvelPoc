package br.com.fioalpha.marvel

import java.math.BigInteger
import java.security.MessageDigest


//ts = 1670545124812
//1968ce91287d6d4ae07efc24c6dd734f

//https://gateway.marvel.com:443/v1/public/characters?ts=1670544152607&apikey=025f2cd3ea0aaa7b9c445d8a5897de59&apikey=a87f69b0d083ef2f3c001d65d45739a8//?//
fun main() {
    val timeStamp = System.currentTimeMillis()
    val apikey = "025f2cd3ea0aaa7b9c445d8a5897de59"
    val apiPrivateKey = "45f27290cbe76b21e99247f8414e8204bb950230"
    generatedCode(apikey, apiPrivateKey, timeStamp).run {
        this
    }
}

fun generatedCode(apikey: String, apiSecret: String, timeStamp: Long): String {
    return "$timeStamp$apiSecret$apikey".md5()
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16)
        .padStart(32, '0')
}