package pl.edu.agh.climant.bytecode.generation

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.bytecode.generation.method.ConstructorGenerator
import pl.edu.agh.climant.bytecode.generation.method.MethodGenerator
import pl.edu.agh.climant.domain.classmembers.ClassDeclaration
import pl.edu.agh.climant.domain.classmembers.Constructor
import pl.edu.agh.climant.domain.classmembers.Method
import pl.edu.agh.climant.domain.classmembers.Field

class ClassGenerator {

    private val CLASS_VERSION: Int = 52
    private val classWriter: ClassWriter = ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS)

    fun generate(classDeclaration: ClassDeclaration) : ClassWriter {
        classWriter.visit(CLASS_VERSION, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, classDeclaration.name, null, "java/lang/Object", null)

        val fields = classDeclaration.fields
        val fieldGenerator = FieldGenerator(classWriter)
        fields.forEach { it.accept(fieldGenerator) }

        val constructor: Constructor = classDeclaration.constructor
        val constructorGenerator =
            ConstructorGenerator(classWriter)
        constructor.accept(constructorGenerator)

        val methods = classDeclaration.methods
        val methodGenerator =
            MethodGenerator(classWriter)
        methods.forEach { it.accept(methodGenerator) }

        classWriter.visitEnd()

        return classWriter
    }
}