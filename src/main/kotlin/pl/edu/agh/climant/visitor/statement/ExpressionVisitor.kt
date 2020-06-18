package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.expression.ConditionalExpression
import pl.edu.agh.climant.domain.statements.expression.Expression

class ExpressionVisitor(private val scope: Scope) : ClimAntBaseVisitor<Expression>() {
    fun visit(ctx: ClimAntParser.VarReferenceContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.ValueContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.FunctionCallContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.ConstructorCallContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.MultiplyContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.SubstractContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.ConditionalExpressionContext): ConditionalExpression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.DivideContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.PowerContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.AddContext): Expression? {
        TODO("Not yet implemented")
    }

    fun visit(ctx: ClimAntParser.ModuloContext?): Statement {
        TODO("Not yet implemented")
    }

}
