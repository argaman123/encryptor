package org.example.algorithm.doubleAlg

import org.example.algorithm.*

class DoublePrompt : EncryptionSetupPrompt() {
    override fun run(): EncryptionMethod<out AlgorithmKey> {
        val firstAlgorithm = AlgorithmMenuPrompt().run().run()
        val secondAlgorithm = AlgorithmMenuPrompt().run().run()
        return DoubleAlgorithm.Encryption(DoubleAlgorithm.Key(firstAlgorithm.key, secondAlgorithm.key))
    }
}