package org.example.algorithm.caesar

import org.example.algorithm.EncryptionSetupPrompt
import org.example.algorithm.xor.XORAlgorithm

class CaesarPrompt : EncryptionSetupPrompt() {
    override fun run() = XORAlgorithm.Encryption()
}