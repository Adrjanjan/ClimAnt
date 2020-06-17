package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.Statement

class PrintStatementVisitor(expressionVisitor: ExpressionVisitor) {
    fun visit(ctx: ClimAntParser.PrintStatementContext): Statement? {
        TODO("Not yet implemented")
    }

}
