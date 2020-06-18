package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.types.Type

class MethodCall(
    var signature: MethodSignature,
    var owner: Expression,
    override var arguments: List<Argument>,
    override val identifier: String,
    override var type: Type
) : Call {

    init {
        this.type = signature.returnType
    }

    constructor(methodSignature: MethodSignature,
                arguments: List<Argument>,
                ownerType: Type) {
        MethodCall(methodSignature, arguments, EmptyExpression(ownerType))
    }

    constructor(methodSignature: MethodSignature,
                arguments: List<Argument>,
                owner: Expression) {
        this.type = signature.returnType
        this.signature = methodSignature
        this.arguments = arguments
        this.owner = owner
    }

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}