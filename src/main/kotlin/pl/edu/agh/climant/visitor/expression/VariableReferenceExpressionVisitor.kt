package pl.edu.agh.climant.visitor.expression

import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.LocalVariableReference

class VariableReferenceExpressionVisitor(private val scope: Scope) {

    fun visit(ctx: ClimAntParser.VarReferenceContext) : LocalVariableReference {
        TODO("Not yet implemented")
    }

}
