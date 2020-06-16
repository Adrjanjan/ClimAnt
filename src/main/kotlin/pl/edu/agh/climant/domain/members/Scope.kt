package pl.edu.agh.climant.domain.members

import com.sun.jdi.connect.Connector.Argument
import pl.edu.agh.climant.domain.MetaData
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.domain.types.Type
import java.util.*
import java.util.stream.Collectors.toList


class Scope {
    private val functionSignatures: MutableList<FunctionSignature>
    private val metaData: MetaData
    private val localVariables: MutableMap<String, LocalVariable>
    private val fields: MutableMap<String, Field?>

    constructor(metaData: MetaData) {
        this.metaData = metaData
        functionSignatures = ArrayList()
        localVariables = mutableMapOf()
        fields = mutableMapOf()
    }

    constructor(scope: Scope) {
        metaData = scope.metaData
        functionSignatures = scope.functionSignatures
        fields = scope.fields
        localVariables = scope.localVariables
    }

    fun addSignature(signature: FunctionSignature) {
        if (isParameterLessSignatureExists(signature.getName())) {
            throw MethodWithNameAlreadyDefinedException(signature)
        }
        functionSignatures.add(signature)
    }

    fun isParameterLessSignatureExists(identifier: String): Boolean {
        return isSignatureExists(identifier, emptyList<Argument>())
    }

    fun isSignatureExists(identifier: String, arguments: List<Argument?>?): Boolean {
        return if (identifier == "super") true else functionSignatures.stream()
            .anyMatch { signature: FunctionSignature ->
                signature.matches(
                    identifier,
                    arguments
                )
            }
    }

    fun getMethodCallSignatureWithoutParameters(identifier: String): FunctionSignature {
        return getMethodCallSignature(identifier, emptyList<Argument>())
    }

    fun getConstructorCallSignature(
        className: String,
        arguments: List<Argument?>
    ): FunctionSignature {
        val isDifferentThanCurrentClass = className != className
        if (isDifferentThanCurrentClass) {
            val argumentsTypes: List<Type> = arguments.stream().map<Any>(Argument::getType)
                .collect(toList())
            return ClassPathScope().getConstructorSignature(className, argumentsTypes)
                .orElseThrow({ MethodSignatureNotFoundException(this, className, arguments) })
        }
        return getConstructorCallSignatureForCurrentClass(arguments)
    }

    private fun getConstructorCallSignatureForCurrentClass(arguments: List<Argument?>): FunctionSignature {
        return getMethodCallSignature(Optional.empty<Type>(), className, arguments)
    }

    fun getMethodCallSignature(
        owner: Optional<Type>,
        methodName: String,
        arguments: List<Argument?>
    ): FunctionSignature {
        val isDifferentThanCurrentClass = owner.isPresent() && !owner.get().equals(classType)
        if (isDifferentThanCurrentClass) {
            val argumentsTypes: List<Type> = arguments.stream().map<Any>(Argument::getType)
                .collect(toList())
            return ClassPathScope().getMethodSignature(owner.get(), methodName, argumentsTypes)
                .orElseThrow({ MethodSignatureNotFoundException(this, methodName, arguments) })
        }
        return getMethodCallSignature(methodName, arguments)
    }

    fun getMethodCallSignature(
        identifier: String,
        arguments: List<Argument?>?
    ): FunctionSignature {
        return if (identifier == "super") {
            FunctionSignature("super", emptyList<Any>(), BultInType.VOID)
        } else functionSignatures.stream()
            .filter { signature: FunctionSignature ->
                signature.matches(
                    identifier,
                    arguments
                )
            }
            .findFirst()
            .orElseThrow<RuntimeException> {
                MethodSignatureNotFoundException(
                    this,
                    identifier,
                    arguments
                )
            }
    }

    private val superClassName: String
        private get() = metaData.getSuperClassName()

    fun addLocalVariable(variable: LocalVariable) {
        localVariables.put(variable.getName(), variable)
    }

    fun getLocalVariable(varName: String?): LocalVariable {
        return Optional.ofNullable<Any>(localVariables.get(varName))
            .orElseThrow<RuntimeException> {
                LocalVariableNotFoundException(
                    this,
                    varName
                )
            }
    }

    fun getLocalVariableIndex(varName: String?): Int {
        return localVariables.indexOf(varName)
    }

    fun isLocalVariableExists(varName: String?): Boolean {
        return localVariables.containsKey(varName)
    }

    fun addField(field: Field) {
        fields[field.getName()] = field
    }

    fun getField(fieldName: String?): Field {
        return Optional.ofNullable(fields[fieldName])
            .orElseThrow<RuntimeException> { FieldNotFoundException(this, fieldName) }
    }

    fun isFieldExists(varName: String?): Boolean {
        return fields.containsKey(varName)
    }

    val className: String
        get() = metaData.getClassName()

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