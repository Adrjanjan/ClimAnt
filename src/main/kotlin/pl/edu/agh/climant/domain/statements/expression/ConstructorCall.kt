package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.types.ClassType
import pl.edu.agh.climant.domain.types.Type
import java.util.*


class ConstructorCall(val className: String, val arguments: List<Parameter> = emptyList()) : Call{

    override val type: Type = ClassType(className)
    override val identifier: String = type.getTypeName()!!

    override fun accept(genrator: ExpressionGenerator) {
        genrator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}