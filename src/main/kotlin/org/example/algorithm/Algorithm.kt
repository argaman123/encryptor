package org.example.algorithm

import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

abstract class AlgorithmKey(val name: String): Serializable {
    class Illegal : java.lang.Exception("Key is invalid.")
    abstract fun encryption(): EncryptionMethod<out AlgorithmKey>
    abstract fun decryption(): DecryptionMethod<out AlgorithmKey>
}

abstract class ByteKey(
    val byte: Byte,
    name: String
) : AlgorithmKey(
    name
)

abstract class AlgorithmMethod<K: AlgorithmKey>(var key: K) : AlgorithmObservable() {
    abstract fun apply(index: Int, byte: Byte): Byte
    abstract fun getOutputFile(original: File): File
    private fun save(original: File, bytes: ByteArray) = getOutputFile(original).writeBytes(bytes)
    open fun apply(file: File){
        notifyObservers(AlgorithmEvent.Started)
        val bytes = file.readBytes()
        bytes.forEachIndexed {i, byte ->
            bytes[i] = apply(i, byte)
        }
        save(file, bytes)
        notifyObservers(AlgorithmEvent.Finished)
    }
}

abstract class EncryptionMethod<K: AlgorithmKey>(k: K) : AlgorithmMethod<K>(k){

    override fun apply(file: File) {
        /*var out = File(file.parent + "/${key.name}-key.bin")
        var i = 1
        while(out.exists()){
            out = File(file.parent + "/${key.name}-key$i.bin")
            i++
        }*/
        println("KEY: $key")
        val f = FileOutputStream(File(file.parent + "/key.bin"))
        val o = ObjectOutputStream(f)
        o.writeObject(key)
        o.close()
        f.close()
        super.apply(file)
    }

    final override fun getOutputFile(original: File) = File(original.absolutePath + ".encrypted")
}

abstract class DecryptionMethod<K: AlgorithmKey>(k: K) : AlgorithmMethod<K>(k){
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
