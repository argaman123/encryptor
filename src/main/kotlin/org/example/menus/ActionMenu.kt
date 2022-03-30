package org.example.menus

class ActionMenu : NumberChoiceMenu<ActionMenu.ActionChoice>(
    ActionChoice.values()
) {
    enum class ActionChoice { Encryption, Decryption }
}