package pl.edu.agh.climant.visitor

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.ClimAntParser.BlockContext
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.classmembers.*
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.visitor.statement.StatementVisitor


class MethodVisitor(scope_out: Scope) : ClimAntBaseVisitor<Method>(){
    private val scope: Scope = scope_out

    override fun visitMethod(ctx: ClimAntParser.MethodContext): Method {
        val methodSignature: MethodSignature = ctx.methodSignature().accept(MethodSignatureVisitor(scope))
        scope.addLocalVariable(LocalVariable(AccessModifier.PUBLIC, "this", scope.classType))
        addParametersAsLocalVariables(methodSignature)
        val block = getBlock(ctx)
        if(methodSignature.name == scope.className){
            return Constructor(AccessModifier.PUBLIC, methodSignature.name, methodSignature.parameters,methodSignature.returnType, block as Block)
        }
        return Method(AccessModifier.PUBLIC, methodSignature, block as Block)
    }

    private fun addParametersAsLocalVariables(signature: MethodSignature) {
        signature.parameters.stream()
            .forEach {
                    param -> scope.addLocalVariable(LocalVariable(AccessModifier.PUBLIC, param.name, param.type))
            }
    }

    private fun getBlock(methodContext: ClimAntParser.MethodContext): Statement? {
        val statementVisitor = StatementVisitor(scope)
        val block: BlockContext = methodContext.methodBody().block()
        return block.accept(statementVisitor)
    }
}