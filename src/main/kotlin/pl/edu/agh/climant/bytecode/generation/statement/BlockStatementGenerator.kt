package pl.edu.agh.climant.bytecode.generation.statement

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.domain.statements.statement.Block

class BlockStatementGenerator(private val mv: MethodVisitor) {

    fun generate(block: Block) {
        val newScope = block.scope
        val statements = block.statements
        val statementGenerator = StatementGenerator(mv, newScope)
        statements.forEach { statement ->
            statement.accept(statementGenerator)
        }
    }

}