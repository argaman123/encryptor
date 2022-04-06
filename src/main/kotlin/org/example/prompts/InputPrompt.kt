package org.example.prompts

import java.lang.Exception

open class InputPrompt<T>(
    private val message: String,
    protected val convert: (String) -> T
) : Prompt<T>() {

    override fun run(): T {
            print(message)
            return convert(input.nextLine())
    }
}