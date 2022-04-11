package org.example.algorithm.reverse

import org.example.algorithm.*
import org.example.algorithm.reverse.ReverseAlgorithm.*

class ReversePrompt : EncryptionSetupPrompt() {
    override fun run(): EncryptionMethod<out AlgorithmKey> {
        val algorithm = AlgorithmMenuPrompt().run().run()
        val key = Key(algorithm.key)
        return Encryption(key)
    }
}