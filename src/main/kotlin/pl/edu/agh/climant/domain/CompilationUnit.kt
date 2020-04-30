package pl.edu.agh.climant.domain

class CompilationUnit(val classDeclaration: ClassDeclaration) {

    fun className() = classDeclaration.name

}