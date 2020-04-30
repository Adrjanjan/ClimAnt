package pl.edu.agh.climant.bytecode.generation

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator
import pl.edu.agh.climant.domain.ClassDeclaration
import pl.edu.agh.climant.domain.CompilationUnit

class BytecodeGenerator {

    fun generate(compilationUnit: CompilationUnit) : ByteArray {
        val classDeclaration: ClassDeclaration = compilationUnit.classDeclaration;
        val classGenerator: ClassGenerator = ClassGenerator()
        return classGenerator.generate(classDeclaration).toByteArray
    }

}