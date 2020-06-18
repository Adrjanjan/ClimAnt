package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.types.ClassType
import pl.edu.agh.climant.domain.types.Type

class ConstructorCall(override var arguments: List<Argument>,
                      override var type: Type,
                      override var identifier: String
) : Call {

    constructor(identifier: String) : this(identifier, listOf())

    constructor(className: String, arguments: List<Argument>) : this(arguments, ClassType(className), className) {
        this.type = ClassType(className)
        this.arguments = arguments
        this.identifier = type.getTypeName()
    }

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}