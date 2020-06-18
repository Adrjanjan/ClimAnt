package pl.edu.agh.climant.domain.types

enum class BuiltInType(
    private val xDname: String,
    private val typeClass: Class<*>?,
    private val descriptor: String,
    private val opcodes: TypeSpecificOpcodes
) :
    Type {
    BOOLEAN(
        "boolean",
        Boolean::class.javaPrimitiveType, "Z", TypeSpecificOpcodes.INT
    ),
    INT(
        "int",
        Int::class.javaPrimitiveType, "I", TypeSpecificOpcodes.INT
    ),
    REAL(
        "double",
        Double::class.javaPrimitiveType, "D", TypeSpecificOpcodes.REAL
    ),
    STRING(
        "string",
        String::class.java, "Ljava/lang/String;", TypeSpecificOpcodes.OBJECT
    ),
    BOOLEAN_ARR(
        "bool[]",
        BooleanArray::class.java, "[B", TypeSpecificOpcodes.OBJECT
    ),
    INT_ARR(
        "int[]",
        IntArray::class.java, "[I", TypeSpecificOpcodes.OBJECT
    ),
    REAL_ARR(
        "double[]",
        DoubleArray::class.java, "[D", TypeSpecificOpcodes.OBJECT
    ),
    STRING_ARR(
        "string[]",
        Array<String>::class.java, "[Ljava/lang/String;", TypeSpecificOpcodes.OBJECT
    ),
    NONE(
        "", null, "", TypeSpecificOpcodes.OBJECT
    ),
    VOID(
        "void", Void.TYPE, "V", TypeSpecificOpcodes.VOID
    );

    override fun getTypeName(): String {
        return xDname
    }

    override fun getTypeClass(): Class<*>? {
        return typeClass
    }

    override fun getDescriptor(): String {
        return descriptor
    }

    override fun getInternalName(): String {
        return descriptor
    }

    override fun getLoadVariableOpcode(): Int {
        return opcodes.load
    }

    override fun getStoreVariableOpcode(): Int {
        return opcodes.store
    }

    override fun getReturnOpcode(): Int {
        return opcodes.`return`
    }

    override fun getAddOpcode(): Int {
        return opcodes.add
    }

    override fun getSubtractOpcode(): Int {
        return opcodes.substract
    }

    override fun getMultiplyOpcode(): Int {
        return opcodes.multiply
    }

    override fun getDivideOpcode(): Int {
        return opcodes.divide
    }

}