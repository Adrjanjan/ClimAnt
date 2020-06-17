package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.Statement

class ReturnStatementVisitor(expressionVisitor: ExpressionVisitor) {
    fun visit(ctx: ClimAntParser.ReturnStatementContext?): Statement {
        TODO("Not yet implemented")
    }

}
