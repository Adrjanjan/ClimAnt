package pl.edu.agh.climant.domain.members

import pl.edu.agh.climant.domain.types.Type

interface Variable {
    fun getType() : Type
    fun getName() : String
}