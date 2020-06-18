package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.domain.statements.expression.Argument
import pl.edu.agh.climant.domain.types.Type
import java.util.function.Predicate
import java.util.stream.IntStream

data class MethodSignature(
    val name: String,
    val parameters: List<Parameter>,
    val returnType: Type
) {

    fun matches(otherSignatureName: String, arguments: List<Argument>): Boolean {
        val namesAreEqual = name == otherSignatureName
        if (!namesAreEqual) {
            return false
        }
        val nonDefaultParametersCount = parameters.stream()
            .filter { p: Parameter -> p.defaultValue == null }
            .count()
        if (nonDefaultParametersCount > arguments.size) {
            return false
        }
        val isNamedArgList = arguments.stream()
            .anyMatch { a: Argument -> a.parameterName != null }
        return if (isNamedArgList) {
            areArgumentsAndParamsMatchedByName(arguments)
        } else areArgumentsAndParamsMatchedByIndex(arguments)
    }

    private fun areArgumentsAndParamsMatchedByIndex(arguments: List<Argument>): Boolean {
        return IntStream.range(0, arguments.size)
            .allMatch { i: Int ->
                val argumentType: Type = arguments[i].type
                val parameterType: Type = parameters[i].type
                argumentType == parameterType
            }
    }

    private fun areArgumentsAndParamsMatchedByName(arguments: List<Argument>): Boolean {
        return arguments.stream().allMatch { a: Argument ->
            val paramName: String = a.parameterName!!
            parameters.stream()
                .map<Any>(Parameter::name)
                .anyMatch { anObject: Any? -> paramName == anObject }
        }
    }

}