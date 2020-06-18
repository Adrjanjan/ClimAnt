package pl.edu.agh.climant.visitor.expression

import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.expression.Expression

class ArithmeticExpressionVisitor(private val expressionVisitor: ExpressionVisitor) {
    fun visit(ctx: ClimAntParser.MultiplyContext): Expression {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.SubstractContext): Expression {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.DivideContext): Expression {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.PowerContext): Expression {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.AddContext): Expression {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.ModuloContext?): Expression {
        TODO("Not yet implemented")
    }

}
