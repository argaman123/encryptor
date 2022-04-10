package org.example.algorithm.reverse

import org.example.algorithm.AlgorithmKey
import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod

class ReverseAlgorithm {

    class Key(var algorithm: AlgorithmKey) : AlgorithmKey(name = "Reverse") {
        override fun encryption() = Encryption(this)
        override fun decryption() = Decryption(this)
        override fun toString() = algorithm.toString()
    }


    class Encryption(k: Key): EncryptionMethod<Key>(k){
        private val decrypt = k.algorithm.decryption()
        override fun apply(index: Int, byte: Byte): Byte = decrypt.apply(index, byte)
    }

    class Decryption(k: Key): DecryptionMethod<Key>(k){
        private val encrypt = k.algorithm.encryption()
        override fun apply(index: Int, byte: Byte): Byte = encrypt.apply(index, byte)
    }

}