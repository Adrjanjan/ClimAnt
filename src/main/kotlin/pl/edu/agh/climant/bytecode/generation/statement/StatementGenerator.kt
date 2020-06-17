package pl.edu.agh.climant.bytecode.generation.statement

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.ConditionalExpression
import pl.edu.agh.climant.domain.statements.expression.EmptyExpression
import pl.edu.agh.climant.domain.statements.expression.Value
import pl.edu.agh.climant.domain.statements.statement.Assignment
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.domain.statements.statement.ReturnStatement
import pl.edu.agh.climant.domain.statements.statement.VariableDeclaration

class StatementGenerator(mv: MethodVisitor, scope: Scope) {

    private val expressionGenerator = ExpressionGenerator(mv, scope)
    private val blockStatementGenerator = BlockStatementGenerator(mv)
    private val returnStatementGenerator = ReturnStatementGenerator(expressionGenerator, mv)
    private val variableDeclarationGenerator = VariableDeclarationStatementGenerator(expressionGenerator, this)
    private val assignmentStatementGenerator = AssignmentStatementGenerator(expressionGenerator, mv, scope)

    fun generate(parameter: Parameter) {
        expressionGenerator.generate(parameter)
    }

    fun generate(block: Block) {
        blockStatementGenerator.generate(block)
    }

    fun generate(returnStatement: ReturnStatement) {
        returnStatementGenerator.generate(returnStatement)
    }

    fun generate(variableDeclaration: VariableDeclaration) {
        variableDeclarationGenerator.generate(variableDeclaration)
    }

    fun generate(assignment: Assignment) {
        assignmentStatementGenerator.generate(assignment)
    }

    fun generate(emptyExpression: EmptyExpression) {
        expressionGenerator.generate(emptyExpression)
    }

    fun generate(value: Value) {
        expressionGenerator.generate(value)
    }

    fun generate(conditionalExpression: ConditionalExpression) {
        TODO("Not yet implemented")
    }
}