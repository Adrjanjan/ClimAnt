package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.types.Type

class Argument(val parameterName: String?,
               override var type: Type,
               val expression: Expression) : Expression {

    override fun accept(generator: ExpressionGenerator) {
        expression.accept(generator)
    }

    override fun accept(generator: StatementGenerator) {
        expression.accept(generator)
    }
}