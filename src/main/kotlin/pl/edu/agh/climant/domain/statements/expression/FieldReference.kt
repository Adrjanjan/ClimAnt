package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.classmembers.Field
import pl.edu.agh.climant.domain.types.Type

class FieldReference(val field: Field,
                     override val name: String,
                     override var type: Type) : Reference {

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

    fun getOwnerInternalName(): String = field.getInternalName()

}