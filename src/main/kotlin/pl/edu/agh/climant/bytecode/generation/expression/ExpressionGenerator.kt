package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.members.Scope
import pl.edu.agh.climant.domain.statements.expression.EmptyExpression

class ExpressionGenerator(private val mv: MethodVisitor,
                          private val scope: Scope) {

    private val parameterExpressionGenerator = ParameterExpressionGenerator(mv, scope)

    fun generate(parameter: Parameter) {
        parameterExpressionGenerator.generate(parameter)
    }

    fun generate(emptyExpression : EmptyExpression){
        // nothing to do!
    }

}
