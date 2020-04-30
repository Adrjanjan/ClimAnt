package pl.edu.agh.climant

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

class Compiler {

    fun main(args: Array<String>) {
        validate(args)
        val bytes = compile(args[0])
        saveToFile(bytes)
    }

    private fun validate(args: Array<String>) {
        TODO("Not yet implemented")
    }

    private fun compile(fileName: String) : Array<Byte> {
        TODO("Not yet implemented")
    }

    private fun saveToFile(bytes: Array<Byte>) {
        TODO("Not yet implemented")
    }
}