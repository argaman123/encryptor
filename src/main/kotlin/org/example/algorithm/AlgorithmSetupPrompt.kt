package org.example.algorithm

import org.example.algorithm.Key
import org.example.prompts.ActionMenuPrompt
import org.example.prompts.ActionMenuPrompt.ActionChoice
import org.example.prompts.Prompt
import org.example.prompts.SourceFilePrompt
import java.io.File

abstract class AlgorithmSetupPrompt (
    actionChoice: ActionChoice? = null
) : Prompt<AlgorithmMethod<out Key>>() {

    val actionChoice = actionChoice ?: ActionMenuPrompt().run()

    fun execute(file: File = SourceFilePrompt().run()) : AlgorithmMethod<out Key> {
        var finalFile = file
        run().let {
            while (it.getOutputFile(finalFile).exists()){
                println("ERROR: output file already exists.")
                finalFile = SourceFilePrompt().run()
            }
            it.apply(file)
            return it
        }
    }
}