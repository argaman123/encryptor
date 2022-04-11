package org.example.prompts

import org.example.algorithm.AlgorithmMethodPrompt
import org.example.algorithm.TimingObserver

class MainPrompt : Prompt<Unit>() {

    override fun run(){
        val algorithmMethod = AlgorithmMethodPrompt().run()
        val file = SourceFilePrompt(algorithmMethod).run()
        algorithmMethod.addObserver(TimingObserver())
        algorithmMethod.apply(file)
    }

}