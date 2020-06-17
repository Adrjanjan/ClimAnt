package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.Statement

class IfStatementVisitor(statementVisitor: StatementVisitor, expressionVisitor: ExpressionVisitor) {
    fun visit(ctx: ClimAntParser.IfStatementContext): Statement? {
        TODO("Not yet implemented")
    }

}
