package pl.edu.agh.climant.visitor.expression

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser.ParameterContext
import pl.edu.agh.climant.ClimAntParser.ParameterWithDefaultValueContext
import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.types.Type
import pl.edu.agh.climant.util.getFromTypeContext


class ParameterExpressionVisitor(private val expressionVisitor: ExpressionVisitor) :
    ClimAntBaseVisitor<Parameter>() {
    override fun visitParameter(ctx: ParameterContext): Parameter {
        val name: String = ctx.identifier().text
        val type: Type = getFromTypeContext(ctx.type())!!
        return Parameter(name, type, null)
    }

    override fun visitParameterWithDefaultValue(ctx: ParameterWithDefaultValueContext): Parameter {
        val name: String = ctx.identifier().text
        val type: Type = getFromTypeContext(ctx.type())!!
        val defaultValue: Expression = ctx.expression().accept(expressionVisitor)
        return Parameter(name, type, defaultValue)
    }

}