package org.example.prompts

import org.example.algorithm.AlgorithmMethod
import java.io.File
import java.lang.Exception

class SourceFilePrompt(algorithm: AlgorithmMethod<*>? = null) : RepeatedInputPrompt<File>(
    "Enter the path of the source file: ",
    BadSourceFile(),
    convert = fun (s): File {
        val file = File(s)
        algorithm?.let {
            if (it.getOutputFile(file).exists()) throw BadSourceFile("Output file already exists.")
        }
        return if (file.isFile) file else throw BadSourceFile()
    }
) {
    class BadSourceFile(s: String = "the source file specified doesn't exist.") : Exception(s)
}