package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.types.Type

class LocalVariable(
    private val accessModifier: AccessModifier,
    private val name: String,
    private val type: Type
) : Variable {
    override fun getAccessModifier() = accessModifier

    override fun getType() = type

    override fun getName() = name
}
