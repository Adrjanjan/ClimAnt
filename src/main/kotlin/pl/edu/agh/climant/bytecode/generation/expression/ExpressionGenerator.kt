package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.*
import pl.edu.agh.climant.domain.statements.expression.arithmetic.Addition
import pl.edu.agh.climant.domain.statements.expression.arithmetic.Division
import pl.edu.agh.climant.domain.statements.expression.arithmetic.Multiplication
import pl.edu.agh.climant.domain.statements.expression.arithmetic.Subtraction

class ExpressionGenerator(private val mv: MethodVisitor,
                          private val scope: Scope
) {

    private val referenceExpressionGenerator = ReferenceExpressionGenerator(mv, scope)
    private val callExpressionGenerator = CallExpressionGenerator(this, mv, scope)
    private val conditionalExpressionGenerator = ConditionalExpressionGenerator(mv, this)
    private val parameterExpressionGenerator = ParameterExpressionGenerator(mv, scope)
    private val valueExpressionGenerator = ValueExpressionGenerator(mv)
    private val arithmeticExpressionGenerator = ArithmeticExpressionGenerator(this, mv)

    fun generate(fieldReference: FieldReference) {
        referenceExpressionGenerator.generate(fieldReference)
    }

    fun generate(localVariableReference: LocalVariableReference) {
        referenceExpressionGenerator.generate(localVariableReference)
    }

    fun generate(conditionalExpression: ConditionalExpression) {
        conditionalExpressionGenerator.generate(conditionalExpression)
    }

    fun generate(parameter: Parameter) {
        parameterExpressionGenerator.generate(parameter)
    }

    fun generate(value: Value) {
        valueExpressionGenerator.generate(value)
    }

    fun generate(methodCall: MethodCall) {
        callExpressionGenerator.generate(methodCall)
    }

    fun generate(constructorCall: ConstructorCall) {
        callExpressionGenerator.generate(constructorCall)
    }

    fun generate(addition: Addition) {
        arithmeticExpressionGenerator.generate(addition)
    }

    fun generate(subtraction: Subtraction) {
        arithmeticExpressionGenerator.generate(subtraction)
    }

    fun generate(multiplication: Multiplication) {
        arithmeticExpressionGenerator.generate(multiplication)
    }

    fun generate(division: Division) {
        arithmeticExpressionGenerator.generate(division)
    }

    fun generate(emptyExpression : EmptyExpression){
        // nothing to do!
    }

}
