package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.Argument

interface Call : Expression {

    val identifier: String

}