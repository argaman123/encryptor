package org.example.algorithm.double

import org.example.algorithm.*
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.algorithm.ByteKey
import org.example.algorithm.Key

class DoublePrompt(actionChoice: ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<DoubleAlgorithm.Key> {
        val firstAlgorithm = AlgorithmMenuPrompt(actionChoice).run().run()
        val secondAlgorithm = AlgorithmMenuPrompt(actionChoice).run().run()
        return when(actionChoice){
            ActionChoice.Decryption -> DoubleAlgorithm.Decryption(firstAlgorithm as DecryptionMethod, secondAlgorithm as DecryptionMethod)
            ActionChoice.Encryption -> DoubleAlgorithm.Encryption(firstAlgorithm as EncryptionMethod, secondAlgorithm as EncryptionMethod)
        }
    }
}