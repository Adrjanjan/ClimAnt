package pl.edu.agh.climant.domain.statements.statement

import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.expression.Expression

class Assignment(variableDeclaration: VariableDeclaration) : Statement {

    var variableName: String = variableDeclaration.variableName
    var expression: Expression = variableDeclaration.expression

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}