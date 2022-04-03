package org.example.prompts

import java.io.File
import java.lang.Exception

class SourceFilePrompt : RepeatedInputPrompt<File>(
    "Enter the path of the source file: ",
    BadSourceFile(),
    fun (s): File? {
        val file = File(s)
        return if (file.isFile) file else null
    }
) {
    class BadSourceFile : Exception("the source file specified doesn't exist.")
}