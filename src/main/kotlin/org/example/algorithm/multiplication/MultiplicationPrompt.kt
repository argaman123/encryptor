package org.example.algorithm.multiplication

import org.example.algorithm.AlgorithmMethod
import org.example.prompts.ActionMenuPrompt
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.caesar.CaesarAlgorithm
import org.example.algorithm.ByteKey
import org.example.algorithm.Key
import org.example.prompts.RepeatedInputPrompt

class MultiplicationPrompt(actionChoice: ActionMenuPrompt.ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<MultiplicationAlgorithm.Key> {
        when(actionChoice){
            ActionMenuPrompt.ActionChoice.Decryption -> {
                val key = RepeatedInputPrompt("Enter the key: ", Key.Illegal()) { s ->
                    try { MultiplicationAlgorithm.Key(s.toByte()) }
                    catch (e: NumberFormatException){ throw Key.Illegal() }
                }.run()
                return MultiplicationAlgorithm.Decryption(key)
            }
            ActionMenuPrompt.ActionChoice.Encryption -> {
                return MultiplicationAlgorithm.Encryption()
            }
        }
    }
}