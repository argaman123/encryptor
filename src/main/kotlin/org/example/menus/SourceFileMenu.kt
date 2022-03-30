package org.example.menus

import java.io.File

class SourceFileMenu : Menu<File>() {

    companion object Errors {
        const val badSourceFile = "ERROR: the source file specified doesn't exist."
    }

    override fun run(): File {
        var file: File
        while (true) {
            print("Enter the path of the source file: ")
            file = File(input.nextLine())
            if (file.exists() && file.isFile) break
            else println(badSourceFile)
        }
        return file
    }
}