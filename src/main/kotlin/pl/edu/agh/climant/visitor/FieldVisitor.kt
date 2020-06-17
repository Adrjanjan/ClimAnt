package pl.edu.agh.climant.visitor

import org.antlr.v4.runtime.misc.NotNull
import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.classmembers.Field
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.types.Type
import pl.edu.agh.climant.domain.types.getFromTypeContext


class FieldVisitor(scope_out: Scope) : ClimAntBaseVisitor<Field>(){
    private val scope: Scope = scope_out

    override fun visitField(@NotNull ctx: ClimAntParser.FieldContext): Field? {
        val accessModifier = AccessModifier.PUBLIC
        val owner: Type = scope.classType
        val type: Type = getFromTypeContext(ctx.type())!!
        val name: String = ctx.SIMPLE_IDENTIFIER().text
        return Field(accessModifier, name, type, owner)
    }
}