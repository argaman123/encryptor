package org.example.algorithm.split

import org.example.algorithm.*


class SplitPrompt : EncryptionSetupPrompt() {
    override fun run(): EncryptionMethod<out AlgorithmKey> {
        val algorithmSetup = AlgorithmMenuPrompt().run()
        val first = algorithmSetup.run()
        val second = algorithmSetup.run()
        val key = SplitAlgorithm.Key(first.key, second.key)
        return SplitAlgorithm.Encryption(key)
    }
}