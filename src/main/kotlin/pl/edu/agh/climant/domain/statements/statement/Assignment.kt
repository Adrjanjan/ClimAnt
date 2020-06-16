package pl.edu.agh.climant.domain.statements.statement

import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.expression.Expression

class Assignment(var variableName: String,
                 var expression: Expression) : Statement {

    constructor(variableDeclaration: VariableDeclaration) {
        this.variableName = variableDeclaration.variableName
        this.expression = variableDeclaration.expression
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}