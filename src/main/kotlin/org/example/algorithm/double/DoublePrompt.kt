package org.example.algorithm.double

import org.example.algorithm.*
import org.example.algorithm.double.DoubleAlgorithm.*

class DoublePrompt : EncryptionSetupPrompt() {
    override fun run(): EncryptionMethod<out AlgorithmKey> {
        val firstAlgorithm = AlgorithmMenuPrompt().run().run()
        val secondAlgorithm = AlgorithmMenuPrompt().run().run()
        return Encryption(Key(firstAlgorithm.key, secondAlgorithm.key))
    }
}