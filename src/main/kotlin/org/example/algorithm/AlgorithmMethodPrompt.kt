package org.example.algorithm

import org.example.prompts.ActionMenuPrompt
import org.example.prompts.Prompt
import org.example.prompts.SourceFilePrompt
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

class AlgorithmMethodPrompt(private val actionChoice: ActionMenuPrompt.ActionChoice = ActionMenuPrompt().run()) :
    Prompt<AlgorithmMethod<out AlgorithmKey>>() {
    companion object {
        fun readKey(file: File): Any {
            val fi = FileInputStream(file)
            val oi = ObjectInputStream(fi)
            val key = oi.readObject()
            oi.close()
            fi.close()
            return key
        }
    }

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