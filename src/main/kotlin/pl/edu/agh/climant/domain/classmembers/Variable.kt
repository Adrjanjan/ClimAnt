package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.types.Type

interface Variable {
    fun getAccessModifier() : AccessModifier
    fun getType() : Type
    fun getName() : String
}