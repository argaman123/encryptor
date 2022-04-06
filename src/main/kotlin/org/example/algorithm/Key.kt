package org.example.algorithm

import kotlin.random.Random

abstract class Key {
    class Illegal : java.lang.Exception("Key is invalid.")
}

open class ByteKey(val byte: Byte = Random.nextBytes(1)[0]) : Key() {
    override fun toString(): String {
        return byte.toString()
    }
}

