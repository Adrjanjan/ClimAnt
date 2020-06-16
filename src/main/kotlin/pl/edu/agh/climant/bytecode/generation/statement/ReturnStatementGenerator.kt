package pl.edu.agh.climant.bytecode.generation.statement

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.domain.statements.statement.ReturnStatement

class ReturnStatementGenerator(private val expressionGenerator: ExpressionGenerator,
                               private val mv: MethodVisitor) {

    fun generate(returnStatement: ReturnStatement) {
        val expression = returnStatement.expression
        val type = expression.type
        expression.accept(expressionGenerator)
        mv.visitInsn(type.getReturnOpcode())
    }

}