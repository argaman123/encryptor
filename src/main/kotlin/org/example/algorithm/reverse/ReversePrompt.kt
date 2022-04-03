package org.example.algorithm.reverse

import org.example.algorithm.*
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.algorithm.key.Key

class ReversePrompt(actionChoice: ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<Key> {
        val action = if(actionChoice == ActionChoice.Decryption) ActionChoice.Encryption else ActionChoice.Decryption
        val algorithm = AlgorithmMenuPrompt(action).run().run()
        return when(actionChoice){
            ActionChoice.Decryption -> ReverseAlgorithm.Decryption(algorithm as EncryptionMethod)
            ActionChoice.Encryption -> ReverseAlgorithm.Encryption(algorithm as DecryptionMethod)
        }
    }
}