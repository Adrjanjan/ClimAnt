package pl.edu.agh.climant.domain.types

class TypeChecker {

    companion object {
        fun isInt(type: Type): Boolean {
            return type == BuiltInType.INT
        }

    }

}