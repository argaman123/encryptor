package org.example

import java.io.File
import java.util.*

class MainMenu {

    companion object Errors {
        const val badActionChoice = "ERROR: choice isn't available."
        const val badSourceFile = "ERROR: the source file specified doesn't exist."
    }

    data class UserChoices (
        val action: ActionChoice,
        val file: File
    ) {
        fun apply() = action.run(file)
    }

    private val reader = Scanner(System.`in`)

    private fun getActionChoice(): ActionChoice {
        val choices = ActionChoice.values()
        choices.forEachIndexed { i, choice ->
            println("(${i + 1}) $choice")
        }
        var actionChoice: Int
        while (true) {
            print("Enter your choice: ")
            actionChoice = reader.nextInt()
            if (1 <= actionChoice && actionChoice <= choices.size) break
            else println(badActionChoice)
        }
        reader.nextLine()
        return choices[actionChoice - 1]
    }

    private fun getFileChoice(): File {
        var file: File
        while (true) {
            print("Enter the path of the source file: ")
            file = File(reader.nextLine())
            if (file.exists() && file.isFile) break
            else println(badSourceFile)
        }
        return file
    }

    fun getUserChoices(): UserChoices {
        return UserChoices(
            action = getActionChoice(),
            file = getFileChoice()
        )
    }

}