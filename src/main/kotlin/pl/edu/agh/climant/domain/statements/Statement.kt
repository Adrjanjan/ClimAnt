package pl.edu.agh.climant.domain.statements

import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator

@FunctionalInterface
interface Statement {
    fun accept(generator : StatementGenerator)
}