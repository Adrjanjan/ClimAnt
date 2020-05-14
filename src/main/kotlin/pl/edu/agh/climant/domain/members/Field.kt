package pl.edu.agh.climant.domain.members

import pl.edu.agh.climant.domain.types.Type

class Field(
    val fieldName: String,
    val typeName: Type,
    val owner: Type
) : Variable{
    override fun getType(): Type {
        return typeName
    }

    override fun getName(): String {
        return fieldName
    }

    fun getInternalName() : String{
        return owner.getInternalName()!!
    }

    fun accept(FieldGenerator generator){
        generator.generate(this)
    }
}