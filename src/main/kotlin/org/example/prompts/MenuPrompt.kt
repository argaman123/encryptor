package org.example.prompts

open class MenuPrompt<T>(
    private val choices: Array<T>,
) : Prompt<T>() {

    companion object Errors {
        const val invalidChoice = "ERROR: choice isn't available."
    }

    override fun run(): T {
        val choices = choices
        choices.forEachIndexed { i, choice ->
            println("(${i + 1}) $choice")
        }
        var actionChoice: Int
        while (true) {
            print("Enter your choice: ")
            actionChoice = input.nextInt()
            if (1 <= actionChoice && actionChoice <= choices.size) break
            else println(invalidChoice)
        }
        input.nextLine()
        return choices[actionChoice - 1]
    }
}