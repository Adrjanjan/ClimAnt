package pl.edu.agh.climant.bytecode.generation.expression

import jdk.internal.org.objectweb.asm.Opcodes
import pl.edu.agh.climant.bytecode.generation.method.getMethodDescriptor
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.Call
import pl.edu.agh.climant.domain.statements.expression.ConstructorCall
import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.statements.expression.MethodCall
import pl.edu.agh.climant.domain.types.ClassType
import java.util.*
import java.util.function.Consumer


class CallExpressionGenerator(
    private val expressionGenerator: ExpressionGenerator,
    val scope: Scope,
    private val methodVisitor: org.objectweb.asm.MethodVisitor
) {

    fun generate(constructorCall: ConstructorCall) {
        val signature: MethodSignature =
            scope.getConstructorCallSignature(constructorCall.identifier, constructorCall.arguments)
        val ownerDescriptor: String = ClassType(signature.name).getDescriptor()
        methodVisitor.visitTypeInsn(Opcodes.NEW, ownerDescriptor)
        methodVisitor.visitInsn(Opcodes.DUP)
        val methodDescriptor: String = getMethodDescriptor(signature)
        generateArguments(constructorCall, signature)
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ownerDescriptor, "<init>", methodDescriptor, false)
    }

    fun generate(methodCall: MethodCall) {
        val owner: Expression = methodCall.owner
        owner.accept(expressionGenerator)
        generateArguments(methodCall)
        val functionName: String = methodCall.identifier
        val methodDescriptor: String = getMethodDescriptor(methodCall.signature)
        val ownerDescriptor: String = methodCall.owner.ide
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ownerDescriptor, functionName, methodDescriptor, false)
    }

    private fun generateArguments(call: MethodCall) {
        val signature: MethodSignature =
            scope.getMethodCallSignature(call.type , call.identifier, call.arguments)
        generateArguments(call, signature)
    }


    private fun generateArguments(call: ConstructorCall) {
        val signature: MethodSignature = scope.getConstructorCallSignature(call.identifier, call.arguments)
        generateArguments(call, signature)
    }

    private fun generateArguments(call: Call, signature: MethodSignature) {
        val parameters: List<Parameter> = signature.parameters
        var arguments: List<Argument> = call.arguments
        arguments = getSortedArguments(arguments, parameters)
        arguments.forEach { it.accept(expressionGenerator) }
        generateDefaultParameters(call, parameters, arguments);
    }

    private fun getSortedArguments(
        arguments: List<Argument>,
        parameters: List<Parameter>
    ): List<Argument> {
        val argumentIndexComparator =
            label@ Comparator { o1: Argument, o2: Argument ->
                if (!o1.parameterName.isPresent()) return@label 0
                getIndexOfArgument(o1, parameters) - getIndexOfArgument(o2, parameters)
            }
        return Ordering.from(argumentIndexComparator).immutableSortedCopy(arguments)
    }

    private fun getIndexOfArgument(
        argument: Argument,
        parameters: List<Parameter>
    ): Int {
        val paramName: String = argument.parameterName!!.get()
        return parameters.stream()
            .filter(Predicate<Parameter> { p: Parameter -> p.getName().equals(paramName) })
            .map(Function<Parameter, R> { o: Parameter -> parameters.indexOf(o) })
            .findFirst()
            .orElseThrow(Supplier<RuntimeException> {
                WrongArgumentNameException(
                    argument,
                    parameters
                )
            })
    }

    private fun generateDefaultParameters(
        call: Call,
        parameters: List<Parameter>,
        arguments: List<Argument>
    ) {
        for (i in arguments.size until parameters.size) {
            val defaultParameter: Expression = parameters[i].defaultValue
            defaultParameter.accept(expressionGenerator)
        }
    }
}