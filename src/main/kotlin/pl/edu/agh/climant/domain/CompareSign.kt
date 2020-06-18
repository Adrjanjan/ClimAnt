package pl.edu.agh.climant.domain

import org.objectweb.asm.Opcodes

enum class CompareSign(val sign: String, val code: Int) {

    EQUAL("==", Opcodes.IFEQ),
    NOT_EQUAL("!=", Opcodes.IFNE),
    LESS("<", Opcodes.IFLT),
    GREATER(">", Opcodes.IFGT),
    LESS_OR_EQUAL("<=", Opcodes.IFLE),
    GREATER_OR_EQUAL(">=", Opcodes.IFGE)

}