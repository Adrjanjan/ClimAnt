package pl.edu.agh.climant.visitor.expression

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.ClimAntParser.ParameterContext
import pl.edu.agh.climant.ClimAntParser.ParameterWithDefaultValueContext
import pl.edu.agh.climant.domain.statements.expression.Parameter
import java.util.*


class ParameterExpressionListVisitor(private val expressionVisitor: ExpressionVisitor) :
    ClimAntBaseVisitor<List<Parameter?>?>() {
    fun visitParametersList(ctx: ClimAntParser.MethodParametersContext): List<Parameter> {
        val paramsCtx: List<ParameterContext> = ctx.parameter()
        val parameterExpressionVisitor = ParameterExpressionVisitor(expressionVisitor)
        val parameters: MutableList<Parameter> = ArrayList<Parameter>()
        val params: List<Parameter> = paramsCtx.map { p -> p.accept(parameterExpressionVisitor) }
        parameters.addAll(params)
        val paramsWithDefaultValueCtx: List<ParameterWithDefaultValueContext> =
            ctx.parameterWithDefaultValue()

        val paramsWithValue: List<Parameter> =
            paramsWithDefaultValueCtx.map { p -> p.accept(parameterExpressionVisitor) }
        parameters.addAll(paramsWithValue)
        return parameters
    }

}