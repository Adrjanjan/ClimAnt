package pl.edu.agh.climant.bytecode.generation.statement

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.statements.statement.Assignment
import pl.edu.agh.climant.domain.statements.statement.VariableDeclaration

class VariableDeclarationStatementGenerator(private val expressionGenerator: ExpressionGenerator,
                                            private val statementGenerator: StatementGenerator) {

    fun generate(variableDeclaration: VariableDeclaration) {
        val expression = variableDeclaration.expression
        expression.accept(expressionGenerator)
        val assignment = Assignment(variableDeclaration)
        assignment.accept(statementGenerator)
    }

}