package org.example.algorithm.multiplication

import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import org.example.algorithm.key.ByteKey
import kotlin.random.Random

class MultiplicationAlgorithm {

    class Key(byte: Byte = randomByte()) : ByteKey(byte) {
        init {
            if(!validate(byte)) throw Illegal()
        }

        private companion object {
            fun validate(key: Byte): Boolean {
                return key % 2 != 0
            }
            fun randomByte(): Byte{
                var key: Byte
                do {
                    key = Random.nextBytes(1)[0]
                } while(!validate(key))
                return key
            }
        }
    }

    private companion object {
        fun MWOConvert(byte: Byte, key: Byte) :Byte = (byte * key).toByte()
    }

    class Encryption(key: Key = Key()) : EncryptionMethod<Key>(key) {
        override fun apply(index: Int, byte: Byte) = MWOConvert(byte, key.byte)
    }

    class Decryption(key: Key) : DecryptionMethod<Key>(key) {
        private val decryptionKey = (Byte.MIN_VALUE..Byte.MAX_VALUE).first {
            (it.toByte() * key.byte).toByte() == 1.toByte()
        }.toByte()
        override fun apply(index: Int, byte: Byte) = MWOConvert(byte, decryptionKey)
    }

}