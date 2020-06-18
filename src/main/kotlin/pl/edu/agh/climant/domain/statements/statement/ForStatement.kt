package pl.edu.agh.climant.domain.statements.statement

import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.types.Type
import pl.edu.agh.climant.domain.types.TypeChecker
import pl.edu.agh.climant.exceptions.UnsupportedForLoopTypes

class ForStatement(
    val iteratorVariable: Statement,
    val startExpression: Expression,
    val endExpression: Expression,
    val loopBody: Statement,
    val iteratorVariableName: String,
    val scope: Scope
) : Statement {

    init {
        val typesAreIntegers = TypeChecker.isInt(startExpression.type) || TypeChecker.isInt(endExpression.type)
        if (!typesAreIntegers) {
            throw UnsupportedForLoopTypes()
        }
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

    fun getType(): Type = startExpression.type

}