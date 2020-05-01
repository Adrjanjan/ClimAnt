package pl.edu.agh.climant.domain

open class Method(val accessModifier: AccessModifier,
                  val name: String,
                  val parameters: Array<Parameter>,
                  val parameterWithDefaultValue: Array<ParameterWithDefaultValue>,
                  val returnType: Type) {
}