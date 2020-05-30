package pl.edu.agh.climant.domain.members

import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.types.Type

class LocalVariable(
        private val accessModifier: AccessModifier,
        private val name: String,
        private val type: Type
) : Variable
{
    override fun getAccessModifier(): AccessModifier {
        return accessModifier
    }

    override fun getType(): Type {
        return type
    }

    override fun getName(): String {
        return name
    }
}
