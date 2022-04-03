package org.example.prompts

class ActionMenuPrompt : MenuPrompt<ActionMenuPrompt.ActionChoice>(
    ActionChoice.values()
) {
    enum class ActionChoice { Encryption, Decryption }
}