package pl.edu.agh.climant.visitor

import org.antlr.v4.runtime.misc.NotNull
import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.ClimAntParser.ClassDeclarationContext
import pl.edu.agh.climant.ClimAntParser.FieldContext
import pl.edu.agh.climant.domain.MetaData
import pl.edu.agh.climant.domain.classmembers.ClassDeclaration
import pl.edu.agh.climant.domain.classmembers.Scope
import java.util.function.Function
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
        val methodsCtx: List<FunctionContext> = ctx.classBody().function()
        val fields = ctx.classBody().field().stream()
            .map { field: FieldContext ->
                field.accept(
                    fieldVisitor
                )
            }
            .peek(scope::addField)
            .collect(toList<Any>())
        methodsCtx.stream()
            .map(Function<FunctionContext, Any> { method: FunctionContext ->
                method.functionDeclaration().accept(functionSignatureVisitor)
            })
            .forEach(scope::addSignature)
        val defaultConstructorExists: Boolean = scope.isParameterLessSignatureExists(name)
        addDefaultConstructorSignatureToScope(name, defaultConstructorExists)
        val methods: MutableList<Function> = methodsCtx.stream()
            .map(Function<FunctionContext, Any> { method: FunctionContext ->
                method.accept(
                    FunctionVisitor(scope)
                )
            })
            .collect(toList())
        if (!defaultConstructorExists) {
            methods.add(getDefaultConstructor())
        }
        val startMethodDefined: Boolean = scope.isParameterLessSignatureExists("start")
        if (startMethodDefined) {
            methods.add(getGeneratedMainMethod())
        }
        return ClassDeclaration(name, fields, methods)
    }
}