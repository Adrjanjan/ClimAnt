package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.statement.IfStatement
import pl.edu.agh.climant.visitor.expression.ExpressionVisitor

class IfStatementVisitor(
    private val statementVisitor: StatementVisitor,
    private val expressionVisitor: ExpressionVisitor
) : ClimAntBaseVisitor<IfStatement>() {

    fun visit(ctx: ClimAntParser.IfStatementContext): IfStatement {
        val expressionContext = ctx.expression()
        val condition = expressionContext.accept(expressionVisitor)
        val trueBlock = ctx.trueStatement.accept(statementVisitor)
        // false statement == falseBlock
        if (ctx.falseStatement != null) return IfStatement(
            condition,
            trueBlock,
            ctx.falseStatement.accept(statementVisitor)
        )
        return IfStatement(condition, trueBlock)
    }

}
