package org.example.prompts

import org.example.algorithm.AlgorithmMenuPrompt
import org.example.algorithm.TimingObserver

class MainPrompt : Prompt<Unit>() {

    override fun run(){
        val algorithmSetup = AlgorithmMenuPrompt().run()
        val algorithmMethod = algorithmSetup.run()
        val file = SourceFilePrompt(algorithmMethod).run()
        algorithmMethod.addObserver(TimingObserver())
        algorithmMethod.apply(file)
    }

}