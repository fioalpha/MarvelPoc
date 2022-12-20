package br.com.fioalpha.marvel

import java.math.BigInteger
import java.security.MessageDigest
import java.security.Timestamp
import java.util.*

//1671059992382
//d1384049098eb2117d7611f237118366
private const val LENGTH_PAD = 32
private const val MD5 = "MD5"
private const val RADIX = 16
private const val PAD_CHAR = '0'
fun main() {

    val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L)

//                  025f2cd3ea0aaa7b9c445d8a5897de59
    val apikey = "025f2cd3ea0aaa7b9c445d8a5897de59"
//                         45f27290cbe76b21e99247f8414e8204bb950230
    val apiPrivateKey = "45f27290cbe76b21e99247f8414e8204bb950230"
    generatedCode(apikey, apiPrivateKey, ts).run {
        this
    }
}

fun generatedCode(apikey: String, apiSecret: String, timeStamp: Long): String {
    return "$timeStamp$apiSecret$apikey".md5()
}

fun String.md5(): String {
    val md = MessageDigest.getInstance(MD5)
    return BigInteger(1, md.digest(this.toByteArray())).toString(RADIX)
        .padStart(LENGTH_PAD, PAD_CHAR)
}
