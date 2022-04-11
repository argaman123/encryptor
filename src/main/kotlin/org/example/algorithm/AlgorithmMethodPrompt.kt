package org.example.algorithm

import org.example.prompts.ActionMenuPrompt
import org.example.prompts.Prompt
import org.example.prompts.SourceFilePrompt

class AlgorithmMethodPrompt(private val actionChoice: ActionMenuPrompt.ActionChoice = ActionMenuPrompt().run()) :
    Prompt<AlgorithmMethod<out AlgorithmKey>>() {
    override fun run() = when (actionChoice) {
        ActionMenuPrompt.ActionChoice.Encryption -> {
            AlgorithmMenuPrompt().run().run()
        }
        ActionMenuPrompt.ActionChoice.Decryption -> {
            val file = SourceFilePrompt(message = "Enter the path of the key: ").run()
            (readKey(file) as AlgorithmKey).decryption()
        }
    }
}