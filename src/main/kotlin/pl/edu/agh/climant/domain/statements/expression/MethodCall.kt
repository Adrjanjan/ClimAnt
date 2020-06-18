package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.types.Type

class MethodCall(
    val signature: MethodSignature,
    val owner: Expression,
    override val arguments: List<Argument>,
    override val identifier: String,
    override var type: Type
) : Call {

    init {
        this.type = signature.returnType
    }

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}