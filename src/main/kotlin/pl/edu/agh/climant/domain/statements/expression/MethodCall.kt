package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.Argument
import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.types.Type

class MethodCall(
    val signature: MethodSignature,
    val arguments: List<Argument>,
    val owner: Expression,
    override val identifier: String,
    override var type: Type
) : Call {

    init {
        this.type = signature.returnType
    }

    override fun accept(generator: ExpressionGenerator) {
        TODO("Not yet implemented")
    }

    override fun accept(generator: StatementGenerator) {
        TODO("Not yet implemented")
    }

}