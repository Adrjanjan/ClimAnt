package pl.edu.agh.climant

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

class ParseExample {

    fun main(args: Array<String>) {
        val charStream = CharStreams.fromFileName("./src/main/antlr/pl/edu/agh/climant/ClimAnt.g4")
        val climAntLexer = ClimAntLexer(charStream)
        val commonTokenStream = CommonTokenStream(climAntLexer)
        val climAntParser = ClimAntParser(commonTokenStream)
        var parseTree: ParseTree = climAntParser.classDeclaration()
        println(parseTree)
    }
}