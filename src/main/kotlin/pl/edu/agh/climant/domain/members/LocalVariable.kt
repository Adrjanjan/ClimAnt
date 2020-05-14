package pl.edu.agh.climant.domain.members

import pl.edu.agh.climant.domain.types.Type

class LocalVariable(
    private val name: String,
    private val type: Type
) : Variable
{
    override fun getType(): Type {
        return type
    }

    override fun getName(): String {
        return name
    }
}
