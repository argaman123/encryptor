package org.example.algorithm.xor

import org.example.algorithm.EncryptionSetupPrompt

class XORPrompt : EncryptionSetupPrompt(){
    override fun run() = XORAlgorithm.Encryption()
}