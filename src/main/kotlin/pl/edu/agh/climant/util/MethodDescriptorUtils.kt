package pl.edu.agh.climant.util

import pl.edu.agh.climant.domain.classmembers.Method
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.types.Type

fun getMethodDescriptor(method: Method): String {
    val parameters = method.parameters
    val returnType = method.returnType
    return getMethodDescriptor(parameters, returnType)
}

fun getMethodDescriptor(signature: MethodSignature): String {
    val parameters = signature.parameters
    val returnType = signature.returnType
    return getMethodDescriptor(parameters, returnType)
}

fun getMethodDescriptor(parameters: Collection<Parameter>, returnType: Type): String {
    val parametersDescriptor: String = parameters.map { parameter ->
        parameter.type.getDescriptor()
    }.joinToString("", "(", ")")

    val returnDescriptor = returnType.getDescriptor()
    return parametersDescriptor + returnDescriptor
}