package org.example.algorithm

import org.example.algorithm.caesar.CaesarPrompt
import org.example.algorithm.double.DoublePrompt
import org.example.algorithm.multiplication.MultiplicationPrompt
import org.example.algorithm.reverse.ReversePrompt
import org.example.algorithm.split.SplitPrompt
import org.example.algorithm.xor.XORPrompt
import org.example.prompts.ActionMenuPrompt
import org.example.prompts.MenuPrompt
import org.example.prompts.Prompt

class AlgorithmMenuPrompt (val actionChoice: ActionMenuPrompt.ActionChoice? = null) : Prompt<AlgorithmSetupPrompt>(){

    private class AlgorithmChoicePrompt (val actionChoice: ActionMenuPrompt.ActionChoice?): MenuPrompt<AlgorithmChoicePrompt.AlgorithmChoice>(
        arrayOf(
            AlgorithmChoice("Caesar", fun() = CaesarPrompt(actionChoice)),
            AlgorithmChoice("XOR", fun() = XORPrompt(actionChoice)),
            AlgorithmChoice("Multiplication", fun() = MultiplicationPrompt(actionChoice)),
            AlgorithmChoice("Double", fun() = DoublePrompt(actionChoice)),
            AlgorithmChoice("Reverse", fun() = ReversePrompt(actionChoice)),
            AlgorithmChoice("Split", fun() = SplitPrompt(actionChoice)),
            )
    ){
        class AlgorithmChoice(val name: String, val get: () -> AlgorithmSetupPrompt) {
            override fun toString() = name
        }
    }

    override fun run(): AlgorithmSetupPrompt = AlgorithmChoicePrompt(actionChoice).run().get()

}