package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.domain.statements.expression.arithmetic.*
import pl.edu.agh.climant.domain.types.BuiltInType

class ArithmeticExpressionGenerator(val expressionGenerator: ExpressionGenerator,
                                    val mv: MethodVisitor) {

    fun generate(expression: Addition) {
        if (expression.type == BuiltInType.STRING) {
            generateStringAppend(expression)
            return
        }
        evaluateArithmeticComponents(expression)
        mv.visitInsn(expression.type.getAddOpcode())
    }

    fun generate(expression: Subtraction) {
        evaluateArithmeticComponents(expression)
        mv.visitInsn(expression.type.getSubtractOpcode())
    }

    fun generate(expression: Multiplication) {
        evaluateArithmeticComponents(expression)
        mv.visitInsn(expression.type.getMultiplyOpcode())
    }

    fun generate(expression: Division) {
        evaluateArithmeticComponents(expression)
        mv.visitInsn(expression.type.getDivideOpcode())
    }

    private fun evaluateArithmeticComponents(expression: ArithmeticExpression) {
        expression.leftExpression.accept(expressionGenerator)
        expression.rightExpression.accept(expressionGenerator)
    }

    private fun generateStringAppend(expression: Addition) {
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder")
        mv.visitInsn(Opcodes.DUP)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)

        val leftExpression = expression.leftExpression
        leftExpression.accept(expressionGenerator)
        val leftExpressionDescriptor = leftExpression.type.getDescriptor()
        var descriptor = "($leftExpressionDescriptor)Ljava/lang/StringBuilder;"
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", descriptor, false)

        val rightExpression = expression.rightExpression
        rightExpression.accept(expressionGenerator)
        val rightExpressionDescriptor = rightExpression.type.getDescriptor()
        descriptor = "($rightExpressionDescriptor)Ljava/lang/StringBuilder;"
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", descriptor, false)

        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
    }

}