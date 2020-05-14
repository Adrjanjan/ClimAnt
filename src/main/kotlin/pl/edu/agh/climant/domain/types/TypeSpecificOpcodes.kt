package pl.edu.agh.climant.domain.types

import com.sun.org.apache.bcel.internal.generic.*
import org.objectweb.asm.Opcodes.*

enum class TypeSpecificOpcodes(
    val load: Int,
    val store: Int,
    val `return`: Int,
    val add: Int,
    val substract: Int,
    val multiply: Int,
    val divide: Int
) {
    INT(
        ILOAD,
        ISTORE,
        IRETURN,
        IADD,
        ISUB,
        IMUL,
        IDIV
    ),
    REAL(
        DLOAD,
        DSTORE,
        DRETURN,
        DADD,
        DSUB,
        DMUL,
        DDIV
    ),
    VOID(
        ALOAD,
        ASTORE,
        RETURN,
        0,
        0,
        0,
        0
    ),
    OBJECT(
        ALOAD,
        ASTORE,
        ARETURN,
        0,
        0,
        0,
        0
    );

}