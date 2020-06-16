package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.types.Type

data class Parameter(val name: String,
                     override val type: Type,
                     val defaultValue: Expression?) : Expression {

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}