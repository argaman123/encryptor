package org.example.algorithm

import org.example.prompts.Prompt
import org.example.prompts.SourceFilePrompt
import java.io.File

abstract class EncryptionSetupPrompt : Prompt<EncryptionMethod<out AlgorithmKey>>() {
    fun execute(file: File = SourceFilePrompt().run()) : AlgorithmMethod<out AlgorithmKey> {
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