package org.example.algorithm.xor

import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import org.example.algorithm.key.ByteKey
import kotlin.experimental.xor

class XORAlgorithm {
    class Encryption(key: ByteKey = ByteKey()): EncryptionMethod<ByteKey>(key){
        override fun apply(index: Int, byte: Byte) :Byte = byte xor key.byte
    }

    class Decryption(key: ByteKey): DecryptionMethod<ByteKey>(key){
        override fun apply(index: Int, byte: Byte) :Byte = byte xor key.byte
    }
}
