package org.example.algorithm.split

import org.example.algorithm.*
import org.example.algorithm.split.SplitAlgorithm.*


class SplitPrompt : EncryptionSetupPrompt() {
    override fun run(): EncryptionMethod<out AlgorithmKey> {
        val algorithmSetup = AlgorithmMenuPrompt().run()
        val first = algorithmSetup.run()
        val second = algorithmSetup.run()
        val key = Key(first.key, second.key)
        return Encryption(key)
    }
}