package pl.edu.agh.climant.visitor.expression

import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.expression.ConditionalExpression

class ConditionalExpressionVisitor(private val expressionVisitor: ExpressionVisitor) {
    fun visit(ctx: ClimAntParser.ConditionalExpressionContext): ConditionalExpression {
        TODO("Not yet implemented")
    }


}
