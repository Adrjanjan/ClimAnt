package pl.edu.agh.climant.parsing

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import pl.edu.agh.climant.ClimAntLexer
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.CompilationUnit
import pl.edu.agh.climant.visitor.CompilationUnitVisitor

class Parser {

    fun parse(fileName: String) : CompilationUnit {
        val charStream = CharStreams.fromFileName(fileName)
        val lexer = ClimAntLexer(charStream)
        val commonTokenStream = CommonTokenStream(lexer)
        val parser = ClimAntParser(commonTokenStream)
        parser.addErrorListener(ClimAntErrorListener())
        return parser.compilationUnit().accept(CompilationUnitVisitor())
    }
}