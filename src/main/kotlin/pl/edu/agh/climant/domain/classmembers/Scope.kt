package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.domain.MetaData
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.ClassType
import pl.edu.agh.climant.domain.types.Type
import pl.edu.agh.climant.exceptions.*
import java.util.*

class Scope {
    private val methods: MutableList<MethodSignature>
    private val metaData: MetaData
    private val localVariables: MutableMap<String, LocalVariable>
    private val fields: MutableMap<String, Field?>

    constructor(metaData: MetaData) {
        this.metaData = metaData
        methods = ArrayList()
        localVariables = mutableMapOf()
        fields = mutableMapOf()
    }

    constructor(scope: Scope) {
        metaData = scope.metaData
        methods = scope.methods
        fields = scope.fields
        localVariables = scope.localVariables
    }

    fun addMethod(methodSignature: MethodSignature) {
        if (methodExists(methodSignature.name)) {
            throw MethodWithNameAlreadyDefinedException(methodSignature)
        }
        methods.add(methodSignature)
    }

    private fun methodExists(identifier: String, arguments: List<Parameter> = listOf()) =
        identifier == "super" ||
                methods.any { methodSignature ->
                    methodSignature.matches(identifier, arguments)
                }

    fun getMethodCallSignature(identifier: String): MethodSignature {
        return getMethodCallSignature(identifier, emptyList())
    }

    fun getConstructorCallSignature(className: String, arguments: List<Parameter>): MethodSignature {
        return if (className == this.className) {
            getConstructorCallSignatureForCurrentClass(arguments)
        } else {
            val argumentsTypes: List<Type> = arguments.map { it.type }
            getConstructorSignature(className, argumentsTypes)
                ?: throw
                ConstructorNotFoundException(this, className, arguments)
        }
    }

    private fun getConstructorCallSignatureForCurrentClass(arguments: List<Parameter>): MethodSignature {
        return getMethodCallSignature(null, className, arguments)
    }

    private fun getMethodCallSignature(owner: Type?, methodName: String, arguments: List<Parameter>): MethodSignature {
        if (owner != null && owner != classType) {
            val argumentsTypes: List<Type> = arguments.map { it.type }
            return getMethodSignature(owner, methodName, argumentsTypes)
                ?: throw MethodSignatureNotFoundException(this, methodName, arguments)
        }
        return getMethodCallSignature(methodName, arguments)
    }

    private fun getMethodCallSignature(identifier: String, arguments: List<Parameter>): MethodSignature {
        return if (identifier == "super") {
            MethodSignature("super", emptyList(), BuiltInType.VOID)
        } else {
            methods.firstOrNull { it.matches(identifier, arguments) }
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
        get() = classType.getInternalName()!!
}