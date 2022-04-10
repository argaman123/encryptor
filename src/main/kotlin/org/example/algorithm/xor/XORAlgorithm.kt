package org.example.algorithm.xor

import org.example.algorithm.ByteKey
import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import kotlin.experimental.xor
import kotlin.random.Random


class XORAlgorithm {

    class Key(byte: Byte) : ByteKey(byte, "XOR") {
        override fun encryption() = Encryption(this)
        override fun decryption() = Decryption(this)
    }

    class Encryption(key: Key = Key(Random.nextBytes(1)[0])) : EncryptionMethod<Key>(key) {
        override fun apply(index: Int, byte: Byte): Byte = byte xor key.byte
    }

    class Decryption(key: Key) : DecryptionMethod<Key>(key) {
        override fun apply(index: Int, byte: Byte): Byte = byte xor key.byte
    }
}
