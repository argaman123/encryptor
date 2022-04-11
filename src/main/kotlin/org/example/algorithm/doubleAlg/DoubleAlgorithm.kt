package org.example.algorithm.doubleAlg

import org.example.algorithm.AlgorithmKey
import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import org.example.algorithm.KeyPair

class DoubleAlgorithm {

    class Key(first: AlgorithmKey, second: AlgorithmKey) : KeyPair(first, second, "Double") {
        override fun encryption() = Encryption(this)
        override fun decryption() = Decryption(this)
    }

    class Encryption(k: Key): EncryptionMethod<Key>(k){
        private val firstEncryption = key.first.encryption()
        private val secondEncryption = key.second.encryption()

        override fun apply(index: Int, byte: Byte): Byte = secondEncryption.apply(index, firstEncryption.apply(index, byte))
    }

    class Decryption(k: Key): DecryptionMethod<Key>(k){
        private val firstDecryption = key.first.decryption()
        private val secondDecryption = key.second.decryption()

        override fun apply(index: Int, byte: Byte): Byte = firstDecryption.apply(index, secondDecryption.apply(index, byte))
    }

}