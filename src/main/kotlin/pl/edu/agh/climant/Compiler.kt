package pl.edu.agh.climant

import pl.edu.agh.climant.bytecode.generation.BytecodeGenerator
import pl.edu.agh.climant.exceptions.Errors.NO_SUCH_FILE
import pl.edu.agh.climant.exceptions.Errors.WRONG_EXTENSION
import pl.edu.agh.climant.parsing.Parser
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

fun main(args: Array<String>) {
    try {
        val compiler = Compiler()
        compiler.validate(args)
        val bytes = compiler.compile(args[0])
        compiler.saveToFile(bytes, args[0])
    } catch (exception: Exception) {
        print(exception)
    }
}

class Compiler {

    @Throws(Exception::class)
    fun validate(args: Array<String>) {
        if (args.size != 1) {
            throw Exception(NO_SUCH_FILE.message)
        }
        val filename = args[0]
        if (!filename.endsWith(".cant")) {
            throw Exception(WRONG_EXTENSION.message)
        }
    }

    fun compile(fileName: String): ByteArray {
        val file = File(fileName)
        val absolutePath = file.absolutePath
        val compilationUnit = Parser().parse(absolutePath)
        return BytecodeGenerator().generate(compilationUnit)
    }

    @Throws(IOException::class)
    fun saveToFile(bytes: ByteArray, fileName: String) {
        val os: OutputStream = FileOutputStream(fileName)
        os.write(bytes)
    }
}