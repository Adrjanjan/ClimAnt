package pl.edu.agh.climant.bytecode.generation.statement

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.domain.statements.statement.PrintStatement
import pl.edu.agh.climant.domain.types.ClassType

class PrintStatementGenerator(val expressionGenerator: ExpressionGenerator,
                              val mv: MethodVisitor) {

    fun generate(printStatement: PrintStatement) {
        val expression = printStatement.expression

        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        expression.accept(expressionGenerator)

        val type = expression.type
        val descriptor = "(" + type.getDescriptor() + ")V"
        val owner = ClassType("java.io.PrintStream")
        val fieldDescriptor = owner.getDescriptor()

        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, fieldDescriptor, "println", descriptor, false)
    }

}