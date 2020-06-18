package pl.edu.agh.climant.domain.statements.expression.arithmetic

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.types.Type

class Multiplication(
    override val leftExpression: Expression,
    override val rightExpression: Expression,
    override var type: Type
) : ArithmeticExpression(leftExpression, rightExpression) {

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}