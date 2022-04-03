package org.example.algorithm.caesar

import org.example.algorithm.AlgorithmMethod
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.key.ByteKey
import org.example.algorithm.key.Key
import org.example.prompts.RepeatedInputPrompt

class CaesarPrompt(actionChoice: ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<ByteKey> {
        return when(actionChoice){
            ActionChoice.Decryption -> {
                val key = RepeatedInputPrompt("Enter the key: ", Key.Illegal()) { s ->
                    try { ByteKey(s.toByte()) }
                    catch (e: NumberFormatException){ null }
                }.run()
                CaesarAlgorithm.Decryption(key)
            }
            ActionChoice.Encryption -> {
                CaesarAlgorithm.Encryption()
            }
        }
    }
}