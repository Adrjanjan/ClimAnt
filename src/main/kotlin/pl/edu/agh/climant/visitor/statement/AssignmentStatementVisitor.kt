package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.statement.Assignment
import pl.edu.agh.climant.domain.statements.statement.VariableDeclaration

class AssignmentStatementVisitor(private val expressionVisitor: ExpressionVisitor) : ClimAntBaseVisitor<Assignment>() {

    fun visit(ctx: ClimAntParser.AssignmentContext): Assignment {
        val expressionContext = ctx.expression()
        val expression = expressionContext.accept(expressionVisitor)
        val name = ctx.identifier().text
        return Assignment(VariableDeclaration(name, expression))
    }

}
