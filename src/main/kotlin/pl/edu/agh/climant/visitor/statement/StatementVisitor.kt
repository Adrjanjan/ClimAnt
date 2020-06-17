package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.expression.ConditionalExpression
import pl.edu.agh.climant.domain.statements.expression.Expression

class StatementVisitor(val scope: Scope) : ClimAntBaseVisitor<Statement>() {

    private val expressionVisitor : ExpressionVisitor = ExpressionVisitor(scope)
    private val printStatementVisitor : PrintStatementVisitor
    private val variableDeclarationStatementVisitor : VariableDeclarationStatementVisitor
    private val returnStatementVisitor : ReturnStatementVisitor
    private val blockStatementVisitor : BlockStatementVisitor
    private val ifStatementVisitor : IfStatementVisitor
    private val forStatementVisitor : ForStatementVisitor
    private val assignmentStatementVisitor : AssignmentStatementVisitor

    init {
        printStatementVisitor = PrintStatementVisitor(expressionVisitor)
        variableDeclarationStatementVisitor = VariableDeclarationStatementVisitor(expressionVisitor, scope)
        returnStatementVisitor = ReturnStatementVisitor(expressionVisitor)
        blockStatementVisitor = BlockStatementVisitor(scope)
        ifStatementVisitor = IfStatementVisitor(this, expressionVisitor)
        forStatementVisitor = ForStatementVisitor(scope)
        assignmentStatementVisitor = AssignmentStatementVisitor(expressionVisitor)
    }

    override fun visitPrintStatement(ctx: ClimAntParser.PrintStatementContext): Statement? {
        return printStatementVisitor.visit(ctx)
    }

    override fun visitReturnStatement(ctx: ClimAntParser.ReturnStatementContext?): Statement {
        return returnStatementVisitor.visit(ctx)
    }

    override fun visitVariableDeclaration(ctx: ClimAntParser.VariableDeclarationContext): Statement? {
        return variableDeclarationStatementVisitor.visit(ctx)
    }

    override fun visitBlock(ctx: ClimAntParser.BlockContext): Statement? {
        return blockStatementVisitor.visit(ctx)
    }

    override fun visitIfStatement(ctx: ClimAntParser.IfStatementContext): Statement? {
        return ifStatementVisitor.visit(ctx)
    }

    override fun visitVarReference(ctx: ClimAntParser.VarReferenceContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitValue(ctx: ClimAntParser.ValueContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitFunctionCall(ctx: ClimAntParser.FunctionCallContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitConstructorCall(ctx: ClimAntParser.ConstructorCallContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitAdd(ctx: ClimAntParser.AddContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitMultiply(ctx: ClimAntParser.MultiplyContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitModulo(ctx: ClimAntParser.ModuloContext?): Statement {
        return expressionVisitor.visit(ctx)
    }

    override fun visitSubstract(ctx: ClimAntParser.SubstractContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitDivide(ctx: ClimAntParser.DivideContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitPower(ctx: ClimAntParser.PowerContext): Expression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitConditionalExpression(ctx: ClimAntParser.ConditionalExpressionContext): ConditionalExpression? {
        return expressionVisitor.visit(ctx)
    }

    override fun visitForStatement(ctx: ClimAntParser.ForStatementContext): Statement? {
        return forStatementVisitor.visit(ctx)
    }

    override fun visitAssignment(ctx: ClimAntParser.AssignmentContext): Statement? {
        return assignmentStatementVisitor.visit(ctx)
    }

}