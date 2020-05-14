package pl.edu.agh.climant.domain

import pl.edu.agh.climant.domain.types.Type

open class Method(val accessModifier: AccessModifier,
                  val name: String,
                  val parameters: Array<Parameter>,
                  val parameterWithDefaultValue: Array<ParameterWithDefaultValue>,
                  val returnType: Type
) {
}