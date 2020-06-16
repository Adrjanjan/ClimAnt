package pl.edu.agh.climant.bytecode.generation

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.domain.Parameter
import pl.edu.agh.climant.domain.members.Scope
import pl.edu.agh.climant.domain.statements.expression.EmptyExpression
import pl.edu.agh.climant.domain.statements.statement.Block
import pl.edu.agh.climant.domain.statements.statement.ReturnStatement

class StatementGenerator(mv: MethodVisitor, scope: Scope) {

    fun generate(block: Block) {
        TODO("Not yet implemented")
    }

    fun generate(returnStatement: ReturnStatement) {
        TODO("Not yet implemented")
    }

    fun generate(emptyExpression: EmptyExpression) {
        TODO("Not yet implemented")
    }

    fun accept(parameter: Parameter) {
        TODO("Not yet implemented")
    }
}