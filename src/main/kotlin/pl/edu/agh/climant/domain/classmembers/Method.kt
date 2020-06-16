package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.bytecode.generation.method.MethodGenerator
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.domain.types.Type

open class Method {
    val accessModifier: AccessModifier
    val methodSignature: MethodSignature
    val methodBody: Block

    constructor(accessModifier: AccessModifier, methodSignature: MethodSignature, methodBody: Block) {
        this.accessModifier = accessModifier
        this.methodSignature = methodSignature
        this.methodBody = methodBody
    }

    constructor(
        accessModifier: AccessModifier,
        name: String,
        parameters: List<Parameter>,
        returnType: Type,
        methodBody: Block
    ) {
        this.accessModifier = accessModifier
        this.methodSignature = MethodSignature(name, parameters, returnType)
        this.methodBody = methodBody
    }

    fun accept(generator: MethodGenerator) {
        generator.generate(this)
    }

    val name: String
        get() = methodSignature.name

    val returnType: Type
        get() = methodSignature.returnType

    val parameters: List<Parameter>
        get() = methodSignature.parameters

}

data class MethodSignature(
    val name: String,
    val parameters: List<Parameter>,
    val returnType: Type
) {
    fun matches(name: String, parameters: List<Parameter>): Boolean {
        return this.name == name && parameters.toSet() == this.parameters.toSet()
    }
}