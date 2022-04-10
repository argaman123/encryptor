package org.example.algorithm.double

import org.example.algorithm.AlgorithmKey
import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod

class DoubleAlgorithm {

    class Key(var first: AlgorithmKey, var second: AlgorithmKey) : AlgorithmKey(name = "Double") {
        override fun encryption() = Encryption(this)
        override fun decryption() = Decryption(this)
        override fun toString() = "$first, $second"
    }

    class Encryption(k: Key): EncryptionMethod<Key>(k){
        private val firstEncryption = key.first.encryption()
        private val secondEncryption = key.second.encryption()

        override fun apply(index: Int, byte: Byte): Byte = firstEncryption.apply(index, secondEncryption.apply(index, byte))
    }

    class Decryption(k: Key): DecryptionMethod<Key>(k){
        private val firstDecryption = key.first.decryption()
        private val secondDecryption = key.second.decryption()

        override fun apply(index: Int, byte: Byte): Byte = secondDecryption.apply(index, firstDecryption.apply(index, byte))
    }

}