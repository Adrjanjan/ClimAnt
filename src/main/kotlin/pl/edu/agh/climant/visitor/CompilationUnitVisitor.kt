package pl.edu.agh.climant.visitor

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.ClassDeclaration
import pl.edu.agh.climant.domain.CompilationUnit

class CompilationUnitVisitor : ClimAntBaseVisitor<CompilationUnit>(){
    @Override
    override fun visitCompilationUnit(ctx: ClimAntParser.CompilationUnitContext?): CompilationUnit {
        return super.visitCompilationUnit(ctx)
    }
}