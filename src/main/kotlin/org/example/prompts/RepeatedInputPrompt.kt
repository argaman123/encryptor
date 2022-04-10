package org.example.prompts

open class RepeatedInputPrompt<T>(
    message: String,
    private vararg val allowedExceptions: Exception,
    convert: (String) -> T
) : InputPrompt<T>(message, convert) {
    override fun run(): T {
        while (true) {
            try {
                return super.run()
            } catch (e: Exception) {
                if (allowedExceptions.any { e::class == it::class }){
                    println("ERROR: ${e.message}")
                }
                else
                    throw e
            }
        }
    }
}