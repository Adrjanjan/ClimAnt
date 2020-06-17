package pl.edu.agh.climant.visitor

import org.antlr.v4.runtime.misc.NotNull
import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.MethodSignature
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.types.getFromTypeContext
import java.util.*

class MethodSignatureVisitor(scope_out: Scope)  : ClimAntBaseVisitor<MethodSignature>() {

    val expressionVisitor = ExpressionVisitor(scope_out)

    override fun visitMethodSignature(@NotNull ctx: ClimAntParser.MethodSignatureContext): MethodSignature{
        val name = ctx.identifier().text
        val returnType = getFromTypeContext(ctx.type())!!
        val parameter = ctx.methodParameters()
        if(parameter!=null){
            val parameters = parameter.accept(ParameterExpressionListVisitor(expressionVisitor))
            return MethodSignature(name, parameters, returnType)
        }
        return MethodSignature(name, Collections.emptyList(), returnType)
    }
}