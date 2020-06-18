package pl.edu.agh.climant.domain.statements.expression.arithmetic

import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.types.BuiltInType

abstract class ArithmeticExpression(
    open val leftExpression: Expression,
    open val rightExpression: Expression) : Expression {

    init {
        this.type = if (rightExpression.type == BuiltInType.STRING) BuiltInType.STRING else leftExpression.type
    }

}