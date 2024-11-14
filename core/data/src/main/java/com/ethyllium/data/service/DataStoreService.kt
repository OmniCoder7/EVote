package com.ethyllium.data.service

import android.content.Context
import com.ethyllium.data.util.CryptoStorage
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class DataStoreService(
    private val context: Context, private val cryptoStorage: CryptoStorage
) {

    companion object {
        const val REFRESH_TOKEN = "refresh_token.txt"
    }

    fun read(fileName: String): String? {
        return try {
            cryptoStorage.decrypt(
                FileInputStream(
                    File(
                        context.filesDir,
                        fileName
                    ).apply { if (!exists()) return null })
            ).decodeToString()
        } catch (_: Exception) {
            null
        }
    }

    fun write(text: String, fileName: String) {
        cryptoStorage.encrypt(
            bytes = text.encodeToByteArray(),
            outputStream = FileOutputStream(File(context.filesDir, fileName).apply {
                if (!exists()) createNewFile()
            }))
    }
}