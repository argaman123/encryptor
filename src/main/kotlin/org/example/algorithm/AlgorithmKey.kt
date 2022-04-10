package org.example.algorithm

import org.example.algorithm.double.DoubleAlgorithm
import java.io.Serializable

abstract class AlgorithmKey(val name: String): Serializable {
    class Illegal : java.lang.Exception("Key is invalid.")
    abstract fun encryption(): EncryptionMethod<out AlgorithmKey>
    abstract fun decryption(): DecryptionMethod<out AlgorithmKey>
}

abstract class ByteKey (
    val byte: Byte,
    name: String
) : AlgorithmKey(
    name
) {
    override fun toString() = byte.toString()
}

abstract class KeyPair(var first: AlgorithmKey, var second: AlgorithmKey, name: String) : AlgorithmKey(name) {
    override fun toString() = "$first, $second"
}