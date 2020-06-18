package pl.edu.agh.climant.util

import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.Type

class TypeChecker {

    companion object {
        fun isInt(type: Type): Boolean {
            return type == BuiltInType.INT
        }

    }

}