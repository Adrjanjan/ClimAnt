package pl.edu.agh.climant.visitor

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.ClimAntVisitor
import pl.edu.agh.climant.domain.ClassDeclaration

class ClassVisitor : ClimAntBaseVisitor<ClassDeclaration>() {
    @Override
    override fun visitClassDeclaration(ctx: ClimAntParser.ClassDeclarationContext?): ClassDeclaration {
        return super.visitClassDeclaration(ctx)
    }
}