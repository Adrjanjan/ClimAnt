package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.domain.CompareSign
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.statements.expression.Argument
import pl.edu.agh.climant.domain.statements.expression.ConditionalExpression
import pl.edu.agh.climant.domain.statements.expression.MethodCall
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.ClassType

class ConditionalExpressionGenerator(val mv: MethodVisitor,
                                     val expressionGenerator: ExpressionGenerator) {

    fun generate(conditionalExpression: ConditionalExpression) {
        val leftExpression = conditionalExpression.leftExpression
        val rightExpression = conditionalExpression.rightExpression
        val compareSign = conditionalExpression.compareSign
        if (conditionalExpression.isPrimitiveComparison) {
            leftExpression.accept(expressionGenerator)
            rightExpression.accept(expressionGenerator)
            mv.visitInsn(Opcodes.ISUB)
        } else {
            val parameter = Parameter(
                "o",
                ClassType("java.lang.Object"),
                null
            )
            val parameters = arrayListOf(parameter)
            val argument = Argument(
                null,
                rightExpression.type,
                rightExpression
            )
            val arguments = arrayListOf(argument)

            when (compareSign) {
                CompareSign.EQUAL, CompareSign.NOT_EQUAL -> {
                    val equalsSignature = MethodSignature("equals", parameters, BuiltInType.BOOLEAN)
                    val equalsCall = MethodCall(equalsSignature, arguments, leftExpression)
                    equalsCall.accept(expressionGenerator)
                    mv.visitInsn(Opcodes.ICONST_1)
                    mv.visitInsn(Opcodes.IXOR)
                }
                CompareSign.LESS, CompareSign.GREATER, CompareSign.LESS_OR_EQUAL, CompareSign.GREATER_OR_EQUAL -> {
                    val compareToSignature = MethodSignature("compareTo", parameters, BuiltInType.INT)
                    val compareToCall = MethodCall(compareToSignature, arguments, leftExpression)
                    compareToCall.accept(expressionGenerator)
                }
            }
        }
        val endLabel = Label()
        val trueLabel = Label()
        mv.visitJumpInsn(compareSign.code, trueLabel)
        mv.visitInsn(Opcodes.ICONST_0)
        mv.visitJumpInsn(Opcodes.GOTO, endLabel)
        mv.visitLabel(trueLabel)
        mv.visitInsn(Opcodes.ICONST_1)
        mv.visitLabel(endLabel)
    }

}