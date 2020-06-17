package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.CompareSign
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.Type
import pl.edu.agh.climant.exceptions.MixedComparisonNotAllowedException

class ConditionalExpression(
    val leftExpression: Expression,
    val rightExpression: Expression,
    val compareSign: CompareSign
) : Expression {

    override var type: Type = BuiltInType.BOOLEAN
    var isPrimitiveComparison: Boolean = false

    init {
        val isLeftExpressionPrimitive = leftExpression.type.getTypeClass()?.isPrimitive!!
        val isRightExpressionPrimitive = rightExpression.type.getTypeClass()?.isPrimitive!!
        this.isPrimitiveComparison = isLeftExpressionPrimitive && isRightExpressionPrimitive
        val isObjectsComparison = !isLeftExpressionPrimitive && !isRightExpressionPrimitive
        if (!isPrimitiveComparison && !isObjectsComparison) {
            throw MixedComparisonNotAllowedException(leftExpression.type, rightExpression.type)
        }
    }

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}