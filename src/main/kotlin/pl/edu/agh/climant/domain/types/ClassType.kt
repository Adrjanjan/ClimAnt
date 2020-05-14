package pl.edu.agh.climant.domain.types

import jdk.internal.org.objectweb.asm.Opcodes
import java.util.*


class ClassType(name: String) : Type {
    private val name: String?
    private val shortcuts: Map<String, String> = mapOf(
        "List" to "java.util.ArrayList"
    )
    override fun getName(): String? {
        return name
    }

    override fun getTypeClass(): Class<*> {
        return try {
            Class.forName(name)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException()
        }
    }

    override fun getDescriptor(): String? {
        return "L" + getInternalName() + ";"
    }

    override fun getInternalName(): String? {
        return name!!.replace(".", "/")
    }

    override fun getLoadVariableOpcode(): Int {
        return Opcodes.ALOAD
    }

    override fun getStoreVariableOpcode(): Int {
        return Opcodes.ASTORE
    }

    override fun getReturnOpcode(): Int {
        return Opcodes.ARETURN
    }

    override fun getAddOpcode(): Int {
        throw RuntimeException("This operation is not implemented")
    }

    override fun getSubstractOpcode(): Int {
        throw RuntimeException("This operation is not implemented")
    }

    override fun getMultiplyOpcode(): Int {
        throw RuntimeException("This operation is not implemented")
    }

    override fun getDividOpcode(): Int {
        throw RuntimeException("This operation is not implemented")
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
        var result = name?.hashCode() ?: 0
        result = 31 * result + shortcuts.hashCode()
        return result
    }


    init {
        this.name = Optional.ofNullable(shortcuts[name]).orElse(name)
    }
}