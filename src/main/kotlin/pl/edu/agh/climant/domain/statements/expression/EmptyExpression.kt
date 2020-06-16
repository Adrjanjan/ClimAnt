package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.StatementGenerator
import pl.edu.agh.climant.domain.types.Type

class EmptyExpression(override val type: Type) : Expression {

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}