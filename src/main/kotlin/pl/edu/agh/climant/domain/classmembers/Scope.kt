package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.domain.MetaData
import pl.edu.agh.climant.domain.statements.expression.Argument
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.ClassType
import pl.edu.agh.climant.domain.types.Type
import pl.edu.agh.climant.exceptions.*
import pl.edu.agh.climant.util.getConstructorSignature
import pl.edu.agh.climant.util.getMethodSignature
import java.util.*

class Scope {
    private val methodSignatures: MutableList<MethodSignature>
    private val metaData: MetaData
    private val localVariables: MutableMap<String, LocalVariable>
    private val fields: MutableMap<String, Field?>

    constructor(metaData: MetaData) {
        this.metaData = metaData
        methodSignatures = ArrayList()
        localVariables = mutableMapOf()
        fields = mutableMapOf()
    }

    constructor(scope: Scope) {
        metaData = scope.metaData
        methodSignatures = scope.methodSignatures
        fields = scope.fields
        localVariables = scope.localVariables
    }

    fun addMethodSignature(methodSignature: MethodSignature) {
        if (methodSignatureExists(methodSignature.name)) {
            throw MethodWithNameAlreadyDefinedException(methodSignature)
        }
        methodSignatures.add(methodSignature)
    }

    fun methodSignatureExists(identifier: String, arguments: List<Argument> = listOf()) =
        identifier == "super" ||
                methodSignatures.any { methodSignature ->
                    methodSignature.matches(identifier, arguments)
                }

    fun getMethodCallSignature(identifier: String): MethodSignature {
        return getMethodCallSignature(identifier, emptyList())
    }

    fun getConstructorCallSignature(className: String, arguments: List<Argument>): MethodSignature {
        return if (className == this.className) {
            getConstructorCallSignatureForCurrentClass(arguments)
        } else {
            val argumentsTypes: List<Type> = arguments.map { it.type }
            getConstructorSignature(className, argumentsTypes)
                ?: throw
                ConstructorNotFoundException(this, className, arguments)
        }
    }

    private fun getConstructorCallSignatureForCurrentClass(arguments: List<Argument>): MethodSignature {
        return getMethodCallSignature(null, className, arguments)
    }

    fun getMethodCallSignature(owner: Type?, methodName: String, arguments: List<Argument>): MethodSignature {
        if (owner != null && owner != classType) {
            val argumentsTypes: List<Type> = arguments.map { it.type }
            return getMethodSignature(owner, methodName, argumentsTypes)
                ?: throw MethodSignatureNotFoundException(this, methodName, arguments)
        }
        return getMethodCallSignature(methodName, arguments)
    }

    private fun getMethodCallSignature(identifier: String, arguments: List<Argument>): MethodSignature {
        return if (identifier == "super") {
            MethodSignature("super", emptyList(), BuiltInType.VOID)
        } else {
            methodSignatures.firstOrNull { it.matches(identifier, arguments) }
                ?: throw MethodSignatureNotFoundException(this, identifier, arguments)
        }
    }

    private val superClassName: String
        get() = metaData.superClassName

    fun addLocalVariable(variable: LocalVariable) {
        localVariables.put(variable.getName(), variable)
    }

    fun getLocalVariable(varName: String): LocalVariable {
        return localVariables[varName] ?: throw NoSuchVariableExists(varName)
    }

    fun getLocalVariableIndex(varName: String): Int {
        for ((index, element) in localVariables.toList().withIndex()) {
            if (element.first == varName) {
                return index
            }
        }
        throw NoSuchVariableExists(varName)
    }

    fun localVariableExists(varName: String?): Boolean {
        return localVariables.containsKey(varName)
    }

    fun addField(field: Field) {
        fields[field.getName()] = field
    }

    fun getField(fieldName: String): Field {
        return Optional.ofNullable(fields[fieldName])
            .orElseThrow { FieldNotFoundException(this, fieldName) }
    }

    fun fieldExists(varName: String?): Boolean {
        return fields.containsKey(varName)
    }

    val className: String
        get() = metaData.className

    val superClassInternalName: String
        get() = ClassType(superClassName).getInternalName()

    val classType: Type
        get() {
            val className = className
            return ClassType(className)
        }

    val classInternalName: String
        get() = classType.getInternalName()
}