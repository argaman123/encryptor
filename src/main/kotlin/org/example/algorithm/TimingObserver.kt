package org.example.algorithm

import java.util.*

class TimingObserver : AlgorithmObserver() {
    private var timeStarted = System.currentTimeMillis()

    override fun update(o: Observable?, arg: Any?) {
        super.update(o, arg)
        val processString = when (o) {
            is EncryptionMethod<*> -> "Encryption"
            is DecryptionMethod<*> -> "Decryption"
            else -> "Process"
        }
        when(arg){
            AlgorithmEvent.Started -> {
                timeStarted = System.currentTimeMillis()
                println("$processString started successfully")
            }
            AlgorithmEvent.Finished -> {
                println("$processString finished successfully, took ${System.currentTimeMillis() - timeStarted}ms")
            }
        }
    }
}