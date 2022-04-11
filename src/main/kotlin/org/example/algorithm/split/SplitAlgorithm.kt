package org.example.algorithm.split

import org.example.algorithm.AlgorithmKey
import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import org.example.algorithm.KeyPair

class SplitAlgorithm {

    class Key(first: AlgorithmKey, second: AlgorithmKey) : KeyPair(first, second, "Split") {
        override fun encryption() = Encryption(this)
        override fun decryption() = Decryption(this)
    }

    class Encryption(k: Key) : EncryptionMethod<Key>(k) {
        private val firstEncryption = key.first.encryption()
        private val secondEncryption = key.second.encryption()

        override fun apply(index: Int, byte: Byte): Byte =
            if (index % 2 == 1) firstEncryption.apply(index, byte) else secondEncryption.apply(index, byte)
    }

    class Decryption(k: Key) : DecryptionMethod<Key>(k) {
        private val firstDecryption = key.first.decryption()
        private val secondDecryption = key.second.decryption()

        override fun apply(index: Int, byte: Byte): Byte =
            if (index % 2 == 1) firstDecryption.apply(index, byte) else secondDecryption.apply(index, byte)
    }

}