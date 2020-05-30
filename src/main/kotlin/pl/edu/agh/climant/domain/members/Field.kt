package pl.edu.agh.climant.domain.members

import pl.edu.agh.climant.bytecode.generation.FieldGenerator
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.types.Type

class Field(
        private val accessModifier: AccessModifier,
        private val fieldName: String,
        private val typeName: Type,
        private val owner: Type
) : Variable {

    override fun getAccessModifier() = accessModifier

    override fun getType() = typeName

    override fun getName() = fieldName

    fun getInternalName() = owner.getInternalName()!!

    fun accept(generator: FieldGenerator){
        generator.generate(this)
    }
}