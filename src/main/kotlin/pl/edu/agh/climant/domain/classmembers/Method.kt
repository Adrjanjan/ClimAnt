package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.bytecode.generation.method.MethodGenerator
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.domain.types.Type

open class Method(val accessModifier: AccessModifier,
                  val methodSignature: MethodSignature,
                  val methodBody: Block) {

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