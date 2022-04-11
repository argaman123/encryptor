package org.example.algorithm

import org.example.algorithm.caesar.CaesarPrompt
import org.example.algorithm.double.DoublePrompt
import org.example.algorithm.multiplication.MultiplicationPrompt
import org.example.algorithm.reverse.ReversePrompt
import org.example.algorithm.split.SplitPrompt
import org.example.algorithm.xor.XORPrompt
import org.example.prompts.MenuPrompt
import org.example.prompts.Prompt

class AlgorithmMenuPrompt : Prompt<EncryptionSetupPrompt>(){

    private class AlgorithmChoicePrompt : MenuPrompt<AlgorithmChoicePrompt.AlgorithmChoice>(
        arrayOf(
            AlgorithmChoice("Caesar", fun() = CaesarPrompt()),
            AlgorithmChoice("XOR", fun() = XORPrompt()),
            AlgorithmChoice("Multiplication", fun() = MultiplicationPrompt()),
            AlgorithmChoice("Double", fun() = DoublePrompt()),
            AlgorithmChoice("Reverse", fun() = ReversePrompt()),
            AlgorithmChoice("Split", fun() = SplitPrompt()),
            )
    ){
        class AlgorithmChoice(val name: String, val get: () -> EncryptionSetupPrompt) {
            override fun toString() = name
        }
    }

    override fun run(): EncryptionSetupPrompt = AlgorithmChoicePrompt().run().get()

}