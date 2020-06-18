package pl.edu.agh.climant.visitor.expression

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.Call
import pl.edu.agh.climant.domain.statements.expression.ConstructorCall
import pl.edu.agh.climant.domain.statements.expression.MethodCall

class CallExpressionVisitor(private val expressionVisitor: ExpressionVisitor, private val scope: Scope) : ClimAntBaseVisitor<Call>() {

    fun visit(ctx: ClimAntParser.FunctionCallContext): MethodCall {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.ConstructorCallContext): ConstructorCall {
        TODO("Not yet implemented")
    }

}
