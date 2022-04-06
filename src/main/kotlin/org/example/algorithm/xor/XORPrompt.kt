package org.example.algorithm.xor

import org.example.algorithm.AlgorithmMethod
import org.example.prompts.ActionMenuPrompt
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.caesar.CaesarAlgorithm
import org.example.algorithm.ByteKey
import org.example.algorithm.Key
import org.example.algorithm.multiplication.MultiplicationAlgorithm
import org.example.prompts.RepeatedInputPrompt

class XORPrompt(actionChoice: ActionMenuPrompt.ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<ByteKey> {
        return when(actionChoice){
            ActionMenuPrompt.ActionChoice.Decryption -> {
                val key = RepeatedInputPrompt("Enter the key: ", Key.Illegal()) { s ->
                    try { ByteKey(s.toByte()) }
                    catch (e: NumberFormatException){ throw Key.Illegal() }
                }.run()
                XORAlgorithm.Decryption(key)
            }
            ActionMenuPrompt.ActionChoice.Encryption -> {
                XORAlgorithm.Encryption()
            }
        }
    }
}