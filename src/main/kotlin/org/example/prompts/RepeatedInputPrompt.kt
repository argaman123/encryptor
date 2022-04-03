package org.example.prompts

import java.lang.Exception

open class RepeatedInputPrompt<T>(
    message: String,
    exception: Exception,
    convert: (String) -> T?
) : InputPrompt<T>(message, exception, convert) {
    override fun run(): T {
        while (true) {
            try {
                return super.run()
            } catch (e: Exception) {
                if (e::class == exception::class){
                    println("ERROR: ${e.message}")
                }
                else
                    throw e
            }
        }
    }
}