package pl.edu.agh.climant.util

import org.apache.commons.lang3.reflect.ConstructorUtils
import org.apache.commons.lang3.reflect.MethodUtils
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.Type
import java.lang.reflect.Constructor
import java.lang.reflect.Method

fun getMethodSignature(
    owner: Type,
    methodName: String,
    argumentsTypes: List<Type>
): MethodSignature? {
    try {
        val ownerType = owner.getTypeClass()!!
        val args = argumentsTypes.map { it.getTypeClass() }.toTypedArray()
        val method = MethodUtils.getMatchingAccessibleMethod(ownerType, methodName, *args)
        return fromMethod(method)
    } finally {
        return null
    }

}

fun getConstructorSignature(
    className: String,
    arguments: List<Type>
): MethodSignature? {
    return try {
        val methodOwnerClass = Class.forName(className)
        val params = arguments.map { it.getTypeClass() }.toTypedArray()
        val constructor = ConstructorUtils.getMatchingAccessibleConstructor(methodOwnerClass, *params)
        fromConstructor(constructor)
    } finally {
        null
    }
}

fun fromMethod(method: Method): MethodSignature {
    val parameters = method.parameters
        .map {
            Parameter(
                it.name,
                getFromTypeName(it.type.canonicalName),
                null
            )
        }
    val returnType = method.returnType
    return MethodSignature(
        method.name,
        parameters,
        getFromTypeName(returnType.canonicalName)
    )
}

fun fromConstructor(constructor: Constructor<*>): MethodSignature? {
    val parameters = constructor.parameters
        .map {
            Parameter(
                it.name,
                getFromTypeName(it.type.canonicalName),
                null
            )
        }
    return MethodSignature(
        constructor.name,
        parameters,
        BuiltInType.VOID
    )
}