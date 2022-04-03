package org.example.algorithm.split

import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import org.example.algorithm.key.Key as KeyClass

class SplitAlgorithm {

    class Key(val first: KeyClass, val second: KeyClass) : KeyClass() {
        override fun toString() = "$first, $second"
    }

    class Encryption(var first: EncryptionMethod<out KeyClass>, var second: EncryptionMethod<out KeyClass>): EncryptionMethod<Key>(Key(first.key, second.key)){
        override fun apply(index: Int, byte: Byte): Byte = if (index % 2 == 1) first.apply(index, byte) else second.apply(index, byte)
    }

    class Decryption(var first: DecryptionMethod<out KeyClass>, var second: DecryptionMethod<out KeyClass>): DecryptionMethod<Key>(Key(first.key, second.key)){
        override fun apply(index: Int, byte: Byte): Byte = if (index % 2 == 1) first.apply(index, byte) else second.apply(index, byte)
    }

}