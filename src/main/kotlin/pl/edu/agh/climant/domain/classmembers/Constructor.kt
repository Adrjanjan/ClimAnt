package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.bytecode.generation.method.ConstructorGenerator
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.Type

class Constructor(
    accessModifier: AccessModifier,
    name: String,
    parameters: List<Parameter>,
    returnType: Type = BuiltInType.VOID,
    methodBody: Block
) : Method(
    accessModifier,
    MethodSignature(name, parameters, returnType),
    methodBody
) {

    fun accept(generator: ConstructorGenerator) {
        generator.generate(this)
    }

}