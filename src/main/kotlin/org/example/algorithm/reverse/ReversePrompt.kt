package org.example.algorithm.reverse

import org.example.algorithm.*

class ReversePrompt : EncryptionSetupPrompt() {
    override fun run(): EncryptionMethod<out AlgorithmKey> {
        val algorithm = AlgorithmMenuPrompt().run().run()
        val key = ReverseAlgorithm.Key(algorithm.key)
        return ReverseAlgorithm.Encryption(key)
    }
}