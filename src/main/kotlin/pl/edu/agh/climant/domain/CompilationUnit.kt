package pl.edu.agh.climant.domain

import pl.edu.agh.climant.domain.classmembers.ClassDeclaration

class CompilationUnit(val classDeclaration: ClassDeclaration) {

    fun className() = classDeclaration.name
}