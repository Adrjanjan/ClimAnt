package pl.edu.agh.climant.domain.statements.expression

interface Call : Expression {

    val identifier: String
    val arguments: List<Argument>

}