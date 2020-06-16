package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.classmembers.MethodSignature

class MethodWithNameAlreadyDefinedException(methodSignature: MethodSignature) : Throwable(
    "Method with this signature is already defined ${methodSignature.name}, ${methodSignature.parameters.joinToString(
        ", ",
        "(",
        ")",
        transform = { it.type.getTypeName()!! })}"
)
