package pl.edu.agh.climant.domain.types

interface Type {
    fun getName(): String?
    fun getTypeClass(): Class<*>?
    fun getDescriptor(): String?
    fun getInternalName(): String?
    fun getLoadVariableOpcode(): Int
    fun getStoreVariableOpcode(): Int
    fun getReturnOpcode(): Int
    fun getAddOpcode(): Int
    fun getSubstractOpcode(): Int
    fun getMultiplyOpcode(): Int
    fun getDividOpcode(): Int
}