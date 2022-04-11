package org.example.algorithm.caesar

import org.example.algorithm.EncryptionSetupPrompt

class CaesarPrompt : EncryptionSetupPrompt() {
    override fun run() = CaesarAlgorithm.Encryption()
}