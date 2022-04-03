package org.example.algorithm.xor

import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import org.example.algorithm.key.ByteKey
import org.example.algorithm.key.Key
import java.io.File
import kotlin.experimental.xor
import kotlin.random.Random

class XORAlgorithm {
    class Encryption(key: ByteKey = ByteKey()): EncryptionMethod<ByteKey>(key){
        override fun apply(index: Int, byte: Byte) :Byte = byte xor key.byte
    }

    class Decryption(key: ByteKey = ByteKey()): DecryptionMethod<ByteKey>(key){
        override fun apply(index: Int, byte: Byte) :Byte = byte xor key.byte
    }
}
