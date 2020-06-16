package pl.edu.agh.climant.visitor

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.Field

class FieldVisitor : ClimAntBaseVisitor<Field>(){
    @Override
    override fun visitField(ctx: ClimAntParser.FieldContext?): Field {
        return super.visitField(ctx)
    }
}