package pl.edu.agh.climant.domain

import pl.edu.agh.climant.bytecode.generation.method.MethodGenerator
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.domain.types.Type

open class Method(val accessModifier: AccessModifier,
                  val name: String,
                  val parameters: List<Parameter>,
                  val parameterWithDefaultValue: List<ParameterWithDefaultValue>,
                  val returnType: Type,
                  val methodBody: Block
) {

    fun accept(generator: MethodGenerator){
        generator.generate(this)
    }

}