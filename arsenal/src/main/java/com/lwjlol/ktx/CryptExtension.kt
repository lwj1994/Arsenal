package com.lwjlol.ktx

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


/*加密相关*/

/**
 * AES 加密
 * @param pwd 加密密码
 */
fun String.aesEncode(pwd: String) = AESCrypt.encrypt(pwd, this)

/**
 * AES 解密
 * @param pwd 加密密码
 */
fun String.aesDecode(pwd: String) = AESCrypt.decrypt(pwd, this)

/**
 * 小写的 32 位 md5
 */
val String.md5: String
    get() = toMd5()

val String.sha1: String
    get() {
        return try {
            val sha = MessageDigest.getInstance("SHA1")
            val byteArray: ByteArray = toByteArray()
            val md5Bytes: ByteArray = sha.digest(byteArray)
            toHex(md5Bytes)
        } catch (e: java.lang.Exception) {
            ""
        }
    }

val String.sha256: String
    get() {
        val digest = MessageDigest.getInstance("SHA-256")
        val result = digest.digest(toByteArray())
        return toHex(result)
    }

/**
 * MD5
 * @param short 短的16位 MD5，长的为32位
 * @param uppercase 是否大写
 */
fun String.toMd5(short: Boolean = false, uppercase: Boolean = false): String {
    try {
        // Create MD5 Hash
        val digest = java.security.MessageDigest.getInstance("MD5")
        digest.update(this.toByteArray())
        val messageDigest = digest.digest()

        // Create Hex String
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hexString.append(h)
        }
        return if (short) {
            hexString.substring(8, 24)
        } else {
            hexString.toString()
        }.apply {
            if (uppercase) {
                toUpperCase(Locale.ENGLISH)
            }
        }
    } catch (e: NoSuchAlgorithmException) {
    }

    return ""
}


/**
 * 转成16进制
 */
fun String.toHex(byteArray: ByteArray): String {
    return with(StringBuilder()) {
        byteArray.forEach {
            val value = it
            val hex = value.toInt() and (0xFF)
            val hexStr = Integer.toHexString(hex)
            //println(hexStr)
            if (hexStr.length == 1) {
                //this.append("0").append(hexStr)
                append("0").append(hexStr)
            } else {
                //this.append(hexStr)
                append(hexStr)
            }
        }
        toString()
    }
}