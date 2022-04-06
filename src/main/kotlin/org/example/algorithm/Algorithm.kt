package org.example.algorithm

import org.example.algorithm.Key
import java.io.File

abstract class AlgorithmMethod<K: Key>(var key: K) : AlgorithmObservable() {
    abstract fun apply(index: Int, byte: Byte): Byte
    abstract fun getOutputFile(original: File): File
    private fun save(original: File, bytes: ByteArray) = getOutputFile(original).writeBytes(bytes)
    fun apply(file: File){
        notifyObservers(AlgorithmEvent.Started)
        val bytes = file.readBytes()
        bytes.forEachIndexed {i, byte ->
            bytes[i] = apply(i, byte)
        }
        save(file, bytes)
        notifyObservers(AlgorithmEvent.Finished)
    }
}

abstract class EncryptionMethod<K: Key>(k: K) : AlgorithmMethod<K>(k){
    init {
        println("KEY: $k")
    }

    final override fun getOutputFile(original: File) = File(original.absolutePath + ".encrypted")
}

abstract class DecryptionMethod<K: Key>(k: K) : AlgorithmMethod<K>(k){
    final override fun getOutputFile(original: File): File {
        val originalFilePath = original.absolutePath.removeSuffix(".encrypted")
        val lastDot = originalFilePath.lastIndexOf(".")
        val originalFileName = originalFilePath.substring(0, lastDot)
        val originalFileExtension = originalFilePath.substring(lastDot)
        return File("${originalFileName}_decrypted$originalFileExtension")
    }
}


/*
abstract class Algorithm {
    class IllegalKey (message :String = "the key is invalid.") : Exception(message)

    protected fun saveEncryption(original: File, bytes: ByteArray){
        File(original.absolutePath + ".encrypted").writeBytes(bytes)
    }

    protected fun saveDecryption(original: File, bytes: ByteArray){
        val originalFilePath = original.absolutePath.removeSuffix(".encrypted")
        val lastDot = originalFilePath.lastIndexOf(".")
        val originalFileName = originalFilePath.substring(0, lastDot)
        val originalFileExtension = originalFilePath.substring(lastDot)
        File("${originalFileName}_decrypted$originalFileExtension").writeBytes(bytes)
    }



    open fun validateKey(key: Key): Boolean {
        return true
    }

    protected abstract fun encrypt(i: Int, byte: Byte): Byte
    fun encrypt(file: File){
        val bytes = file.readBytes()
        bytes.forEachIndexed {i, byte ->
            bytes[i] = encrypt(i, byte)
        }
        saveEncryption(file, bytes)
    }

    protected abstract fun decrypt(i: Int, byte: Byte): Byte
    fun decrypt(file: File){
        val bytes = file.readBytes()
        bytes.forEachIndexed {i, byte ->
            bytes[i] = decrypt(i, byte)
        }
        saveDecryption(file, bytes)
    }

}*/

/*fun randomKey(): Byte {
    var key: Byte
    do {
        key = Random.nextBytes(1)[0]
    } while(!validateKey(key))
    return key
}

protected fun transform(file: File, convert: (Int, Byte) -> Number): ByteArray{
    val bytes = file.readBytes()
    bytes.forEachIndexed {i, byte ->
        bytes[i] = convert(i, byte).toByte()
    }
    return bytes
}*/
