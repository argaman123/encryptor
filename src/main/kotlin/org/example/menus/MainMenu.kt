package org.example.menus

class MainMenu : Menu<Unit>() {

    override fun run(){
        val action = ActionMenu().run()
        val file = SourceFileMenu().run()
        val algorithm = AlgorithmMenu().run()
        algorithm.run(file, action)
    }

}