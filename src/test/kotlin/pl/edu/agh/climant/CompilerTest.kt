package pl.edu.agh.climant

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.Test
import org.junit.Assert.assertNotNull;

class CompilerTest {

    @Test
    fun isLexerAndParserGenerated() {
        val charStream = CharStreams.fromFileName("./example/Example.cant")
        val climAntLexer = ClimAntLexer(charStream)
        val commonTokenStream = CommonTokenStream(climAntLexer)
        val climAntParser = ClimAntParser(commonTokenStream)
        val parseTree: ParseTree = climAntParser.compilationUnit()
        assertNotNull(parseTree)
    }
}