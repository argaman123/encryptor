package org.example.algorithm.xor

import org.example.algorithm.AlgorithmMethod
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.xor.XORAlgorithm.*
import org.example.prompts.ActionMenuPrompt
import org.example.prompts.SourceFilePrompt

class XORPrompt(actionChoice: ActionMenuPrompt.ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<Key> {
        return when(actionChoice){
            ActionMenuPrompt.ActionChoice.Decryption -> {
                /*val key = RepeatedInputPrompt("Enter the key: ", Key.Illegal()) { s ->
                    try { ByteKey(s.toByte()) }
                    catch (e: NumberFormatException){ throw Key.Illegal() }
                }.run()*/
                val file = SourceFilePrompt(message = "Enter the path of the key: ").run()
                Decryption(readKey(file) as Key)
            }
            ActionMenuPrompt.ActionChoice.Encryption -> {
                Encryption()
            }
        }
    }
}