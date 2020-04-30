package pl.edu.agh.climant.bytecode.generation

import pl.edu.agh.climant.domain.CompilationUnit

class BytecodeGenerator {

    fun generate(compilationUnit: CompilationUnit) : ByteArray {
        val classDeclaration = compilationUnit.classDeclaration
        val classGenerator = ClassGenerator()
        return classGenerator.generate(classDeclaration).toByteArray()
    }

}