package org.example.algorithm.split

import org.example.algorithm.AlgorithmMenuPrompt
import org.example.algorithm.AlgorithmMethod
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.split.SplitAlgorithm.*
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.prompts.SourceFilePrompt


class SplitPrompt(actionChoice: ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice) {
    override fun run(): AlgorithmMethod<Key> {
        return when (actionChoice) {
            ActionChoice.Decryption -> {
                val file = SourceFilePrompt(message = "Enter the path of the key: ").run()
                Decryption(readKey(file) as Key)
            }
            ActionChoice.Encryption -> {
                val algorithmSetup = AlgorithmMenuPrompt(actionChoice).run()
                val first = algorithmSetup.run()
                val second = algorithmSetup.run()
                val key = Key(first.key, second.key)
                Encryption(key)
            }
        }
    }
}