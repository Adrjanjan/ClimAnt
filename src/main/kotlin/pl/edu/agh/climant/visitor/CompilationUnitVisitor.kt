package pl.edu.agh.climant.visitor

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.CompilationUnit


class CompilationUnitVisitor : ClimAntBaseVisitor<CompilationUnit>(){
    @Override
    override fun visitCompilationUnit(ctx: ClimAntParser.CompilationUnitContext?): CompilationUnit {
        val classVisitor = ClassVisitor()
        val classDeclarationContext = ctx!!.classDeclaration()
        val classDeclaration = classDeclarationContext.accept(classVisitor)
        return CompilationUnit(classDeclaration)
    }
}