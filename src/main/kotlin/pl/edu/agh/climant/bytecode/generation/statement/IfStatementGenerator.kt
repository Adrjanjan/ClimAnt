package pl.edu.agh.climant.bytecode.generation.statement

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.domain.statements.statement.IfStatement

class IfStatementGenerator(val expressionGenerator: ExpressionGenerator,
                           val mv: MethodVisitor,
                           val statementGenerator: StatementGenerator) {

    fun generate(ifStatement: IfStatement) {
        val condition = ifStatement.condition
        condition.accept(expressionGenerator)
        val trueLabel = Label()
        val endLabel = Label()

        mv.visitJumpInsn(Opcodes.IFNE, trueLabel)
        val falseStatement = ifStatement.falseStatement
        falseStatement?.accept(statementGenerator)

        mv.visitJumpInsn(Opcodes.GOTO, endLabel)
        mv.visitLabel(trueLabel)
        ifStatement.trueStatement.accept(statementGenerator)
        mv.visitLabel(endLabel)
    }

}