package org.example.algorithm.split

import org.example.algorithm.*
import org.example.algorithm.double.DoubleAlgorithm
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.algorithm.key.Key

class SplitPrompt(actionChoice: ActionChoice? = null) : AlgorithmSetupPrompt(actionChoice){
    override fun run(): AlgorithmMethod<SplitAlgorithm.Key> {
        val algorithmSetup = AlgorithmMenuPrompt(actionChoice).run()
        val first = algorithmSetup.run()
        val second = algorithmSetup.run()
        return when(actionChoice){
            ActionChoice.Decryption -> SplitAlgorithm.Decryption(first as DecryptionMethod, second as DecryptionMethod)
            ActionChoice.Encryption -> SplitAlgorithm.Encryption(first as EncryptionMethod, second as EncryptionMethod)
        }
    }
}