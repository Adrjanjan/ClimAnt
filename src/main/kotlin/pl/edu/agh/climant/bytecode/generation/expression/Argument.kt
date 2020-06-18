package pl.edu.agh.climant.bytecode.generation.expression

import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.types.Type

class Argument(val parameterName: String?,
               override var type: Type,
               val expression: Expression) : Expression {

    override fun accept(generator: ExpressionGenerator) {
        TODO("Not yet implemented")
    }

    override fun accept(generator: StatementGenerator) {
        TODO("Not yet implemented")
    }
}