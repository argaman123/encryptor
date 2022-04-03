package org.example.algorithm

import org.example.algorithm.key.Key
import org.example.prompts.ActionMenuPrompt
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.prompts.Prompt
import java.io.File

abstract class AlgorithmSetupPrompt (
    actionChoice: ActionChoice? = null
) : Prompt<AlgorithmMethod<out Key>>() {

    val actionChoice = actionChoice ?: ActionMenuPrompt().run()

    fun execute(file: File) : AlgorithmMethod<out Key> {
        run().let {
            it.apply(file)
            return it
        }
    }
}