package pl.edu.agh.climant.visitor

import org.antlr.v4.runtime.misc.NotNull
import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.ClimAntParser.ClassDeclarationContext
import pl.edu.agh.climant.ClimAntParser.FieldContext
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.MetaData
import pl.edu.agh.climant.domain.classmembers.*
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.expression.ConstructorCall
import pl.edu.agh.climant.domain.statements.expression.Expression
import pl.edu.agh.climant.domain.statements.expression.MethodCall
import pl.edu.agh.climant.domain.statements.expression.Parameter
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.domain.types.BuiltInType
import pl.edu.agh.climant.visitor.statement.StatementVisitor
import java.util.*
import java.util.stream.Collectors.toList


class ClassVisitor : ClimAntBaseVisitor<ClassDeclaration>() {

    lateinit var scope: Scope

    override fun visitClassDeclaration(@NotNull ctx: ClassDeclarationContext): ClassDeclaration? {
        val metaData =
            MetaData(ctx.identifier().text, "java.lang.Object")
        scope = Scope(metaData)
        val name: String = ctx.identifier().text
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
        //TODO
//        methodsCtx.stream()
//            .map { method: ClimAntParser.MethodContext ->
//                method.methodSignature().accept(
//                    methodSignatureVisitor
//                )
//            }
//            .forEach(scope::addMethodSignature)

        val defaultConstructorExists: Boolean = scope.methodSignatureExists(name)
        addDefaultConstructorSignatureToScope(name, defaultConstructorExists)

        val methods = methodsCtx.stream()
            .map { method: ClimAntParser.MethodContext ->
                method.accept<Method>(
                    MethodVisitor(scope)
                )
            }
            .collect(toList<Method>())


        val startMethodDefined: Boolean = scope.methodSignatureExists("start")
        if (startMethodDefined) {
            methods.add(getGeneratedMainMethod())
        }

        val constructor = ctx.classBody().constructor()
        //TODO add parameters to constructor
        return ClassDeclaration(AccessModifier.PUBLIC, name, fields, Constructor(AccessModifier.PUBLIC, name, mutableListOf<Parameter>(),BuiltInType.VOID, getBlock(ctx) as Block), methods)
    }

    private fun addDefaultConstructorSignatureToScope(name: String, defaultConstructorExists: Boolean) {
        if (!defaultConstructorExists) {
            val constructorSignature = MethodSignature(name, emptyList<Parameter>(), BuiltInType.VOID)
            scope.addMethodSignature(constructorSignature)
        }
    }

    private fun getGeneratedMainMethod(): Method {
        val args = Parameter("args", BuiltInType.STRING_ARR, null)
        val functionSignature = MethodSignature("main", listOf<Parameter>(args), BuiltInType.VOID)
        val constructorCall = ConstructorCall(scope.className)
        val startFunSignature = MethodSignature("start", emptyList<Parameter>(), BuiltInType.VOID)
        val startFunctionCall = MethodCall(startFunSignature, emptyList(), scope.classType)
        val block = Block(Scope(scope), listOf(constructorCall, startFunctionCall))
        return Method(AccessModifier.PUBLIC, functionSignature, block)
    }

    private fun getBlock(ctx: ClassDeclarationContext): Statement? {
        val statementVisitor = StatementVisitor(scope)
        val block: ClimAntParser.BlockContext = ctx.classBody().constructor().methodBody().block()
        return block.accept(statementVisitor)
    }
}