package org.example.algorithm.reverse

import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import org.example.algorithm.Key

class ReverseAlgorithm {

    class Encryption(var algorithm: DecryptionMethod<out Key>): EncryptionMethod<Key>(algorithm.key){
        override fun apply(index: Int, byte: Byte): Byte = algorithm.apply(index, byte)
    }

    class Decryption(var algorithm: EncryptionMethod<out Key>): DecryptionMethod<Key>(algorithm.key){
        override fun apply(index: Int, byte: Byte): Byte = algorithm.apply(index, byte)
    }

}