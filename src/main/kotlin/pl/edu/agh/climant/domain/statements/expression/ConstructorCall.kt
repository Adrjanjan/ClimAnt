package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.types.ClassType
import pl.edu.agh.climant.domain.types.Type

class ConstructorCall(val className: String,
                      override val arguments: List<Argument>,
                      override val identifier: String,
                      override var type: Type
) : Call {

    init {
        val type: Type = ClassType(className)
        val identifier: String = type.getTypeName()
    }

    override fun accept(generator: ExpressionGenerator) {
//        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
//        generator.generate(this)
    }
}