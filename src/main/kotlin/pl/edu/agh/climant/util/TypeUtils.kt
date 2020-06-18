package pl.edu.agh.climant.util

import org.apache.commons.lang3.StringUtils
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.Type


fun getFromTypeContext(typeContext: ClimAntParser.TypeContext?): Type? {
    return if (typeContext == null) BuiltInType.VOID else getFromTypeName(
        typeContext.getText()
    )
}

fun getFromTypeName(typeName: String): Type {
    if (typeName == "java.lang.String") return BuiltInType.STRING
    return getBuiltInType(typeName)
}

fun getFromValue(value: ClimAntParser.ValueContext): Type? {
    val stringValue: String = value.text
    if (StringUtils.isEmpty(stringValue)) return BuiltInType.VOID
    if (value.NumberLiteral() != null) {
        if (stringValue.toIntOrNull() != null) {
            return BuiltInType.INT
        } else if (stringValue.toFloatOrNull() != null) {
            return BuiltInType.REAL
        }
    } else if (value.BoolLiteral() != null) {
        return BuiltInType.BOOLEAN
    }
    return BuiltInType.STRING
}

fun getValueFromString(stringValue: String, type: Type): Any? {
    return when (type) {
        BuiltInType.INT -> stringValue.toIntOrNull()
        BuiltInType.REAL -> stringValue.toFloatOrNull()
        BuiltInType.BOOLEAN -> stringValue.toBoolean()
        BuiltInType.STRING -> StringUtils.removeEnd(StringUtils.removeStart(stringValue, "\""), "\"")
        else -> null
    }
}

fun getBuiltInType(typeName: String): BuiltInType {
    return try {
        BuiltInType.values().first { it.name == typeName }
    } finally {
        BuiltInType.STRING
    }
}