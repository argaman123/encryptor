package org.example.prompts

import org.example.algorithm.AlgorithmMenuPrompt

class MainPrompt : Prompt<Unit>() {

    override fun run(){
        val file = SourceFilePrompt().run()
        val algorithmSetup = AlgorithmMenuPrompt().run()
        val algorithm = algorithmSetup.execute(file)
    }

}