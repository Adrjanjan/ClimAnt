package pl.edu.agh.climant.domain.statements.statement

import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.expression.Expression
import java.util.*


class IfStatement(
    private val condition: Expression,
    private val trueStatement: Statement,
    private val falseStatement: Statement? = null
) : Statement {

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}