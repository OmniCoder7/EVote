package com.ethyllium.data.util

import android.annotation.SuppressLint
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.ethyllium.data.BuildConfig
import com.orhanobut.logger.Logger
import org.slf4j.LoggerFactory
import java.io.InputStream
import java.io.OutputStream
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

@SuppressLint("InlinedApi")
class CryptoStorage {
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val encryptCipher: Cipher by lazy {
        Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, getKey())
        }
    }

    private fun getDecryptCipherForIv(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry(KEY_ALIAS, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    @Throws(IllegalStateException::class)
    fun encrypt(bytes: ByteArray, outputStream: OutputStream): ByteArray {
        return try {
            val encryptedBytes = encryptCipher.doFinal(bytes)
            outputStream.use {
                it.write(encryptCipher.iv.size)
                it.write(encryptCipher.iv)
                it.write(encryptedBytes.size)
                it.write(encryptedBytes)
            }
            encryptedBytes
        } catch (e: Exception) {
            logger.error("Failed to encrypt data", e)
            throw IllegalStateException("Failed to encrypt data", e)
        }
    }

    @Throws(IllegalStateException::class)
    fun decrypt(inputStream: InputStream): ByteArray {
        return try {
            inputStream.use {
                val ivSize = it.read()
                val iv = ByteArray(ivSize)
                it.read(iv)

                val encryptedBytesSize = it.read()
                val encryptedBytes = ByteArray(encryptedBytesSize)
                it.read(encryptedBytes)

                getDecryptCipherForIv(iv).doFinal(encryptedBytes)
            }
        } catch (e: Exception) {
            Logger.e(e, "Failed to decrypt data")
            throw Exception("Failed to decrypt data", e)
        }
    }

    companion object {
        private const val KEY_ALIAS = BuildConfig.KEY_ALIAS
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"

        private val logger = LoggerFactory.getLogger(CryptoStorage::class.java)
    }
}