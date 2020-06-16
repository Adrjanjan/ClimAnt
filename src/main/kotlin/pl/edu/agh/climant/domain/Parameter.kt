package pl.edu.agh.climant.domain

import pl.edu.agh.climant.bytecode.generation.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.StatementGenerator
import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.types.Type

data class Parameter(val name: String, override val type: Type) : Expression {

    override fun accept(generator: ExpressionGenerator) {
        generator.accept(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.accept(this)
    }
}