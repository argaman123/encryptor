package org.example.prompts

import org.example.algorithm.AlgorithmMethod
import java.io.File

class SourceFilePrompt(algorithm: AlgorithmMethod<*>? = null, message: String = "Enter the path of the source file: ")
    : RepeatedInputPrompt<File>(
    message,
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