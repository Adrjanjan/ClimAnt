package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.bytecode.generation.method.ConstructorGenerator
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.domain.types.Type

class Constructor(
    accessModifier: AccessModifier,
    name: String,
    parameters: List<Parameter>,
    returnType: Type,
    methodBody: Block
) : Method(
    accessModifier,
    name,
    parameters,
    returnType,
    methodBody
) {

    fun accept(generator: ConstructorGenerator) {
        generator.generate(this)
    }

}