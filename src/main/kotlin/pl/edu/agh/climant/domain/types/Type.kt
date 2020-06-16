package pl.edu.agh.climant.domain.types

interface Type {
    fun getTypeName(): String?
    fun getTypeClass(): Class<*>?
    fun getDescriptor(): String?
    fun getInternalName(): String?
    fun getLoadVariableOpcode(): Int
    fun getStoreVariableOpcode(): Int
    fun getReturnOpcode(): Int
    fun getAddOpcode(): Int
    fun getSubtractOpcode(): Int
    fun getMultiplyOpcode(): Int
    fun getDivideOpcode(): Int
}