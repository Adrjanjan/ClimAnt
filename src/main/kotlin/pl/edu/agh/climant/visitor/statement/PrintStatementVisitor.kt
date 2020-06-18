package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.statement.PrintStatement
import pl.edu.agh.climant.visitor.expression.ExpressionVisitor

class PrintStatementVisitor(private val expressionVisitor: ExpressionVisitor) : ClimAntBaseVisitor<PrintStatement>() {

    fun visit(ctx: ClimAntParser.PrintStatementContext): PrintStatement {
        return PrintStatement(ctx.expression().accept(expressionVisitor))
    }

}
