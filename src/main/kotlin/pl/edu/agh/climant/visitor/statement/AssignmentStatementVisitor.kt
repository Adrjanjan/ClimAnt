package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.Statement

class AssignmentStatementVisitor(expressionVisitor: ExpressionVisitor) {
    fun visit(ctx: ClimAntParser.AssignmentContext): Statement? {
        TODO("Not yet implemented")
    }

}
