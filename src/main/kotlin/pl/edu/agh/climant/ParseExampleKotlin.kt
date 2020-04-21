package pl.edu.agh.climant

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

class ParseExampleKotlin {

    fun main(args: Array<String>) {
        val charStream = CharStreams.fromFileName("./src/main/antlr/Erlang.g4")
        val erlangLexer = ErlangLexer(charStream)
        val commonTokenStream = CommonTokenStream(erlangLexer)
        val erlangParser = ErlangParser(commonTokenStream)

        var parseTree: ParseTree = erlangParser.forms()

        println("done")
    }

}