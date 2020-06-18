package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.types.Type

interface Expression : Statement{

    var type: Type
    fun accept(generator : ExpressionGenerator)

}