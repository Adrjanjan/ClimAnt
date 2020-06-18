package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.AccessModifier.PUBLIC
import pl.edu.agh.climant.domain.classmembers.LocalVariable
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.statement.Assignment
import pl.edu.agh.climant.domain.statements.statement.ForStatement
import pl.edu.agh.climant.domain.statements.statement.VariableDeclaration
import pl.edu.agh.climant.visitor.expression.ExpressionVisitor


class ForStatementVisitor(private val scope: Scope)  : ClimAntBaseVisitor<ForStatement>() {

    private val expressionVisitor = ExpressionVisitor(scope)

    fun visit(ctx: ClimAntParser.ForStatementContext): ForStatement {
        val scope = Scope(this.scope)
        val conditionsCtx = ctx.forConditions()
        val start = conditionsCtx.startExpr.accept(expressionVisitor)
        val end = conditionsCtx.endExpr.accept(expressionVisitor)
        val iteratorCtx = conditionsCtx.iterator
        val statementVisitor = StatementVisitor(scope)
        val name = iteratorCtx.text
        if(scope.localVariableExists(name)){
            val iteratorVar = Assignment(VariableDeclaration(name, start))
            val forBody = ctx.statement().accept(statementVisitor)
            return ForStatement(iteratorVar, start, end, forBody, name, scope)
        } else {
            scope.addLocalVariable(LocalVariable(PUBLIC, name, start.type))
            val iteratorVar = VariableDeclaration(name, start)
            val forBody = ctx.statement().accept(statementVisitor)
            return ForStatement(iteratorVar, start, end, forBody, name, scope)
        }

    }

}
