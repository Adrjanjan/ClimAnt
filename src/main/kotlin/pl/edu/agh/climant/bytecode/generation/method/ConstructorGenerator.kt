package pl.edu.agh.climant.bytecode.generation.method

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.bytecode.generation.statement.StatementGenerator
import pl.edu.agh.climant.domain.Constructor

class ConstructorGenerator(private val classWriter: ClassWriter?) {

    fun generate(constructor: Constructor) {
        val block = constructor.methodBody
        val scope = block.scope
        val access = Opcodes.ACC_PUBLIC
        val description = getMethodDescriptor(constructor)
        val methodVisitor = classWriter!!.visitMethod(access, "<init>", description, null, null)
        methodVisitor.visitCode()
        val statementGenerator = StatementGenerator(methodVisitor, scope)
        block.accept(statementGenerator)
        MethodGenerator.appendReturn(constructor, block, statementGenerator)
        methodVisitor.visitMaxs(-1, -1)
        methodVisitor.visitEnd()
    }

}