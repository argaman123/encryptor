package org.example.menus

import org.example.algorithms.Algorithm
import org.example.algorithms.CaesarAlgorithm

class AlgorithmMenu :Menu<Algorithm>(){

    private class AlgorithmChoiceMenu: NumberChoiceMenu<AlgorithmChoiceMenu.AlgorithmChoice>(
        AlgorithmChoice.values()
    ){
        enum class AlgorithmChoice() {
            Caesar { override fun get() = CaesarAlgorithm() };
            abstract fun get(): Algorithm
        }
    }

    override fun run(): Algorithm = AlgorithmChoiceMenu().run().get()

}