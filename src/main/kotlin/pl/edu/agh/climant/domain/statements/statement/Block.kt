package pl.edu.agh.climant.domain.statements.statement

import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.members.Scope

class Block(val scope: Scope, val statements: List<Statement>) : Statement {

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

    fun emptyBlock(scope: Scope): Block {
        return Block(scope, listOf())
    }

}