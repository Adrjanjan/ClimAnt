package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.classmembers.LocalVariable
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.statement.VariableDeclaration
import pl.edu.agh.climant.visitor.expression.ExpressionVisitor

class VariableDeclarationStatementVisitor(private val expressionVisitor: ExpressionVisitor, private val scope: Scope) : ClimAntBaseVisitor<VariableDeclaration>() {

    fun visit(ctx: ClimAntParser.VariableDeclarationContext): Statement {
        val variableName = ctx.identifier().text
        val expression = ctx.expression().accept(expressionVisitor)
        scope.addLocalVariable(LocalVariable(AccessModifier.PUBLIC, variableName, expression.type))
        return VariableDeclaration(variableName, expression)
    }

}
