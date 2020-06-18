package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.*

class ExpressionGenerator(private val mv: MethodVisitor,
                          private val scope: Scope
) {

    private val referenceExpressionGenerator = ReferenceExpressionGenerator(mv, scope)
    private val conditionalExpressionGenerator = ConditionalExpressionGenerator(mv, this)
    private val parameterExpressionGenerator = ParameterExpressionGenerator(mv, scope)
    private val valueExpressionGenerator = ValueExpressionGenerator(mv)

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

    fun generate(emptyExpression : EmptyExpression){
        // nothing to do!
    }

    fun generate(value: Value) {
        valueExpressionGenerator.generate(value)
    }

}
