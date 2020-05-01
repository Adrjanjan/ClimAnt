package pl.edu.agh.climant.domain

import org.objectweb.asm.Opcodes

enum class AccessModifier(val type: String, val value: Int) {

    PRIVATE("private", Opcodes.ACC_PRIVATE),
    PUBLIC("public", Opcodes.ACC_PUBLIC)

}