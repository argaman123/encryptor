package org.example.algorithm.multiplication

import org.example.algorithm.AlgorithmMethod
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.multiplication.MultiplicationAlgorithm.*
import org.example.prompts.ActionMenuPrompt
import org.example.prompts.SourceFilePrompt


class MultiplicationPrompt(actionChoice: ActionMenuPrompt.ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<Key> {
        return when(actionChoice){
            ActionMenuPrompt.ActionChoice.Decryption -> {
                val file = SourceFilePrompt(message = "Enter the path of the key: ").run()
                Decryption(readKey(file) as Key)
            }
            ActionMenuPrompt.ActionChoice.Encryption -> {
                Encryption()
            }
        }
    }
}