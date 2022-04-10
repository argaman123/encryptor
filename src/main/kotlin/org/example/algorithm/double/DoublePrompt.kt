package org.example.algorithm.double

import org.example.algorithm.AlgorithmMenuPrompt
import org.example.algorithm.AlgorithmMethod
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.double.DoubleAlgorithm.*
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.prompts.SourceFilePrompt

class DoublePrompt(actionChoice: ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice) {
    override fun run(): AlgorithmMethod<Key> {
        return when (actionChoice) {
            ActionChoice.Decryption -> {
                val file = SourceFilePrompt(message = "Enter the path of the key: ").run()
                Decryption(readKey(file) as Key)
            }
            ActionChoice.Encryption -> {
                val firstAlgorithm = AlgorithmMenuPrompt(actionChoice).run().run()
                val secondAlgorithm = AlgorithmMenuPrompt(actionChoice).run().run()
                val key = Key(firstAlgorithm.key, secondAlgorithm.key)
                Encryption(key)
            }
        }
    }
}