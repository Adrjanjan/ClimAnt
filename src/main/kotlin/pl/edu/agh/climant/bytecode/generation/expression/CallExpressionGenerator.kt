package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.util.getMethodDescriptor
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.*
import pl.edu.agh.climant.domain.types.ClassType
import pl.edu.agh.climant.exceptions.WrongArgumentNameException
import pl.edu.agh.climant.exceptions.WrongMethodCallArguments

class CallExpressionGenerator(val expressionGenerator: ExpressionGenerator,
                              val mv: MethodVisitor,
                              val scope: Scope) {

    fun generate(constructorCall: ConstructorCall) {
        val signature = scope.getConstructorCallSignature(constructorCall.identifier, constructorCall.arguments)
        val ownerDescriptor = ClassType(signature.name).getDescriptor()

        mv.visitTypeInsn(Opcodes.NEW, ownerDescriptor)
        mv.visitInsn(Opcodes.DUP)

        val methodDescriptor = getMethodDescriptor(signature)
        generateArguments(constructorCall, signature)

        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, ownerDescriptor, "<init>", methodDescriptor, false)
    }

    fun generate(methodCall: MethodCall) {
        val owner = methodCall.owner
        owner.accept(expressionGenerator)

        generateArguments(methodCall)

        val methodName = methodCall.identifier
        val methodDescriptor = getMethodDescriptor(methodCall.signature)
        val ownerDescriptor = methodCall.owner.type.getInternalName()

        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ownerDescriptor, methodName, methodDescriptor, false)
    }

    private fun generateArguments(methodCall: MethodCall) {
        val signature = scope.getMethodCallSignature(methodCall.owner.type, methodCall.identifier, methodCall.arguments)
        generateArguments(methodCall, signature)
    }

    private fun generateArguments(call: Call, signature: MethodSignature) {
        val parameters = signature.parameters
        var arguments = call.arguments

        if (arguments.size > parameters.size) {
            throw WrongMethodCallArguments(call)
        }

        arguments = getSortedArguments(arguments, parameters)
        arguments.forEach { argument ->
            argument.accept(expressionGenerator)
        }
        generateDefaultParameters(call, parameters, arguments)
    }

    private fun getSortedArguments(arguments: List<Argument>, parameters: List<Parameter>): List<Argument> {
        return arguments.sortedWith ( Comparator { a, b ->
            when {
                (a.parameterName == null) -> 0
                else -> getIndexOfArgument(a, parameters) - getIndexOfArgument(b, parameters)
            }
        })
    }


    private fun getIndexOfArgument(argument: Argument, parameters: List<Parameter>): Int {
        val paramName: String = argument.parameterName!!
        return parameters.filter { p: Parameter -> p.name == paramName }
            .map { o: Parameter -> parameters.indexOf(o) }
            .stream().findFirst().orElseThrow{ WrongArgumentNameException(argument, parameters) }
    }

    private fun generateDefaultParameters(call: Call, parameters: List<Parameter>, arguments: List<Argument>) {
        for (i in arguments.size until parameters.size) {
            val defaultParameter: Expression = parameters[i].defaultValue ?: throw WrongMethodCallArguments(call)
            defaultParameter.accept(expressionGenerator)
        }
    }

}
