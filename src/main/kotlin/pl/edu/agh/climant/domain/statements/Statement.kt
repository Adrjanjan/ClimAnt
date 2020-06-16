package pl.edu.agh.climant.domain.statements

import pl.edu.agh.climant.bytecode.generation.StatementGenerator

@FunctionalInterface
interface Statement {
    fun accept(generator : StatementGenerator)
}