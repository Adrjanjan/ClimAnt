package pl.edu.agh.climant.visitor

import org.antlr.v4.runtime.misc.NotNull
import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.ClimAntParser.ClassDeclarationContext
import pl.edu.agh.climant.ClimAntParser.FieldContext
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.MetaData
import pl.edu.agh.climant.domain.classmembers.*
import pl.edu.agh.climant.domain.statements.statement.Block
import java.util.*
import java.util.stream.Collectors.toList


class ClassVisitor : ClimAntBaseVisitor<ClassDeclaration>() {

    lateinit var scope: Scope

    override fun visitClassDeclaration(@NotNull ctx: ClassDeclarationContext): ClassDeclaration? {
        val metaData =
            MetaData(ctx.identifier().getText(), "java.lang.Object")
        scope = Scope(metaData)
        val name: String = ctx.identifier().getText()
        val fieldVisitor = FieldVisitor(scope)
        val methodSignatureVisitor = MethodVisitor(scope)
        val methodsCtx: List<ClimAntParser.MethodContext> = ctx.classBody().method()
        val fields = ctx.classBody().field().stream()
            .map { field: FieldContext ->
                field.accept(
                    fieldVisitor
                )
            }
            .peek(scope::addField)
            .collect(toList<Field>())
        methodsCtx.stream()
            .map { method: ClimAntParser.MethodContext ->
                method.methodSignature().accept(
                    methodSignatureVisitor
                )
            }
            .forEach(scope::addMethod)

        val defaultConstructorExists: Boolean = scope.methodExists(name)
        addDefaultConstructorSignatureToScope(name, defaultConstructorExists)

        val methods = methodsCtx.stream()
            .map { method: ClimAntParser.MethodContext ->
                method.accept<Method>(
                    MethodVisitor(scope)
                )
            }
            .collect(toList<Method>())
        val startMethodDefined: Boolean = scope.methodExists("start")
        if (startMethodDefined) {
            methods.add(getGeneratedMainMethod())
        }
        return ClassDeclaration(AccessModifier.PUBLIC, name, fields, methods)
    }

    private fun addDefaultConstructorSignatureToScope(
        name: String,
        defaultConstructorExists: Boolean
    ) {
        if (!defaultConstructorExists) {
            val constructorSignature = MethodSignature(name, emptyList<Parameter>(), BultInType.VOID)
            scope.addSignature(constructorSignature)
            scope.add
        }
    }

    private fun getGeneratedMainMethod(): Method {
        val args = Parameter("args", BultInType.STRING_ARR, Optional.empty())
        val functionSignature = MethodSignature("main", listOf<Parameter>(args), BultInType.VOID)
        val constructorCall = ConstructorCall(scope.className)
        val startFunSignature = MethodSignature("start", emptyList<Parameter>(), BultInType.VOID)
        val startFunctionCall =
            FunctionCall(startFunSignature, emptyList(), scope.classType)
        val block = Block(Scope(scope), Arrays.asList(constructorCall, startFunctionCall))
        return Function(functionSignature, block)
    }

}