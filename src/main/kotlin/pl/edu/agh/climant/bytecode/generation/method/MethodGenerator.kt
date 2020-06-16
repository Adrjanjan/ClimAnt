package pl.edu.agh.climant.bytecode.generation.method

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.bytecode.generation.StatementGenerator
import pl.edu.agh.climant.domain.Method
import pl.edu.agh.climant.domain.statements.statement.Block

class MethodGenerator(private val classWriter: ClassWriter?) {
    companion object {
        val startProgramMethodName = "start"
    }

    fun generate(method: Method) {
        val name = method.name
        val isMain = name == startProgramMethodName
        val description: String = getMethodDescriptor(method)
        val block = method.classBody
        val scope = block.scope
        val access: Int = Opcodes.ACC_PUBLIC + if (isMain) Opcodes.ACC_STATIC else 0
        val mv = classWriter!!.visitMethod(access, name, description, null, null)
        mv.visitCode()
        val statementGenerator = StatementGenerator(mv, scope)
        block.accept(statementGenerator)
        appendReturn(method, block, statementGenerator)
        mv.visitMaxs(-1, -1)
        mv.visitEnd()
    }

    private fun appendReturn(method: Method, block: Block, statementGenerator: StatementGenerator) {
        TODO("Not yet implemented")
    }
}