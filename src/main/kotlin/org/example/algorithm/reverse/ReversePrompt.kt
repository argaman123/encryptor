package org.example.algorithm.reverse

import org.example.algorithm.AlgorithmMenuPrompt
import org.example.algorithm.AlgorithmMethod
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.reverse.ReverseAlgorithm.*
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.prompts.SourceFilePrompt

class ReversePrompt(actionChoice: ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice) {
    override fun run(): AlgorithmMethod<Key> {
        return when (actionChoice) {
            ActionChoice.Decryption -> {
                val file = SourceFilePrompt(message = "Enter the path of the key: ").run()
                Decryption(readKey(file) as Key)
            }
            ActionChoice.Encryption -> {
                val algorithm = AlgorithmMenuPrompt(actionChoice).run().run()
                val key = Key(algorithm.key)
                Encryption(key)
            }
        }
    }
}