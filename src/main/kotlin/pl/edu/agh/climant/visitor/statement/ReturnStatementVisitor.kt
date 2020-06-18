package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.expression.EmptyExpression
import pl.edu.agh.climant.domain.statements.statement.ReturnStatement
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.visitor.expression.ExpressionVisitor

class ReturnStatementVisitor(private val expressionVisitor: ExpressionVisitor) : ClimAntBaseVisitor<ReturnStatement>() {

    fun visit(ctx: ClimAntParser.ReturnStatementContext): ReturnStatement {
        val expression = ctx.expression().accept(expressionVisitor)
        return ReturnStatement(expression)
    }

    fun visitVoid(ctx: ClimAntParser.ReturnStatementContext): ReturnStatement {
        return ReturnStatement(EmptyExpression(BuiltInType.VOID))
    }

}
