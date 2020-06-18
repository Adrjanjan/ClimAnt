package pl.edu.agh.climant.domain.types

import org.objectweb.asm.Opcodes
import java.util.*


class ClassType(name: String) : Type {
    private val name: String
    private val shortcuts: Map<String, String> = mapOf(
        "List" to "java.util.ArrayList"
    )

    init {
        this.name = Optional.ofNullable(shortcuts[name]).orElse(name)
    }

    override fun getTypeName() = name

    override fun getTypeClass() = try {
        Class.forName(name)
    } catch (e: ClassNotFoundException) {
        throw RuntimeException()
    }

    override fun getDescriptor() = "L" + getInternalName() + ";"


    override fun getInternalName() = name.replace(".", "/")


    override fun getLoadVariableOpcode() = Opcodes.ALOAD


    override fun getStoreVariableOpcode() = Opcodes.ASTORE


    override fun getReturnOpcode() = Opcodes.ARETURN


    override fun getAddOpcode(): Int {
        throw RuntimeException("Add operation is not implemented")
    }

    override fun getSubtractOpcode(): Int {
        throw RuntimeException("Substract operation is not implemented")
    }

    override fun getMultiplyOpcode(): Int {
        throw RuntimeException("Multiply operation is not implemented")
    }

    override fun getDivideOpcode(): Int {
        throw RuntimeException("Divide operation is not implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClassType

        if (name != other.name) return false
        if (shortcuts != other.shortcuts) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode() ?: 0
        result = 31 * result + shortcuts.hashCode()
        return result
    }
}