package org.example.algorithm.caesar

import org.example.algorithm.AlgorithmMethod
import org.example.algorithm.AlgorithmSetupPrompt
import org.example.algorithm.caesar.CaesarAlgorithm.*
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.prompts.SourceFilePrompt

class CaesarPrompt(actionChoice: ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<Key> {
        return when(actionChoice){
            ActionChoice.Decryption -> {
                /*val key = RepeatedInputPrompt("Enter the key: ", Key.Illegal()) { s ->
                    try { ByteKey(s.toByte()) }
                    catch (e: NumberFormatException){ throw Key.Illegal() }
                }.run()
                CaesarAlgorithm.Decryption(key)*/
                val file = SourceFilePrompt(message = "Enter the path of the key: ").run()
                Decryption(readKey(file) as Key)
            }
            ActionChoice.Encryption -> {
                Encryption()
            }
        }
    }
}