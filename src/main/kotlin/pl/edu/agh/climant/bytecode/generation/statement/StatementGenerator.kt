package pl.edu.agh.climant.bytecode.generation.statement

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.*
import pl.edu.agh.climant.domain.statements.statement.*

class StatementGenerator(mv: MethodVisitor, scope: Scope) {

    private val expressionGenerator = ExpressionGenerator(mv, scope)
    private val printStatementGenerator = PrintStatementGenerator(expressionGenerator, mv)
    private val blockStatementGenerator = BlockStatementGenerator(mv)
    private val returnStatementGenerator = ReturnStatementGenerator(expressionGenerator, mv)
    private val variableDeclarationGenerator = VariableDeclarationStatementGenerator(expressionGenerator, this)
    private val ifStatementGenerator = IfStatementGenerator(expressionGenerator, mv, this)
    private val forStatementGenerator = ForStatementGenerator(mv)
    private val assignmentStatementGenerator = AssignmentStatementGenerator(expressionGenerator, mv, scope)

    fun generate(printStatement: PrintStatement) {
        printStatementGenerator.generate(printStatement)
    }

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

    fun generate(forStatement: ForStatement) {
        forStatementGenerator.generate(forStatement)
    }

    fun generate(assignment: Assignment) {
        assignmentStatementGenerator.generate(assignment)
    }

    fun generate(emptyExpression: EmptyExpression) {
        expressionGenerator.generate(emptyExpression)
    }

    fun generate(localVariableReference: LocalVariableReference) {
        expressionGenerator.generate(localVariableReference)
    }

    fun generate(fieldReference: FieldReference) {
        expressionGenerator.generate(fieldReference)
    }

    fun generate(conditionalExpression: ConditionalExpression) {
        expressionGenerator.generate(conditionalExpression)
    }

    fun generate(value: Value) {
        expressionGenerator.generate(value)
    }

    fun generate(methodCall: MethodCall) {
        expressionGenerator.generate(methodCall)
    }

    fun generate(ifStatement: IfStatement) {
        ifStatementGenerator.generate(ifStatement)
    }
}