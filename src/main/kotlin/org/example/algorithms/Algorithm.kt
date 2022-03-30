package org.example.algorithms

import org.example.menus.ActionMenu
import java.io.File

abstract class Algorithm {

    class IllegalKey : java.lang.Exception()

    protected abstract fun encrypt(file: File)
    protected abstract fun decrypt(file: File)
    fun run(file: File, actionChoice: ActionMenu.ActionChoice){
        when(actionChoice){
            ActionMenu.ActionChoice.Encryption -> encrypt(file)
            ActionMenu.ActionChoice.Decryption -> decrypt(file)
        }
    }
}