package pl.edu.agh.climant.bytecode.generation.method

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.bytecode.generation.StatementGenerator
import pl.edu.agh.climant.domain.Constructor
import pl.edu.agh.climant.domain.statements.statement.Block

class ConstructorGenerator(private val classWriter: ClassWriter?) {

    fun generate(constructor: Constructor) {
        val block = constructor.classBody
        val scope = block.scope
        val access = Opcodes.ACC_PUBLIC
        val description = getMethodDescriptor(constructor)
        val methodVisitor = classWriter!!.visitMethod(access, "<init>", description, null, null)
        methodVisitor.visitCode()
        val statementGenerator = StatementGenerator(methodVisitor, scope)
        block.accept(statementGenerator)
        appendReturn(constructor, block, statementGenerator)
        methodVisitor.visitMaxs(-1, -1)
        methodVisitor.visitEnd()
    }

    private fun appendReturn(constructor: Constructor, block: Block, statementGenerator: StatementGenerator) {
        TODO("Not yet implemented")
    }
}