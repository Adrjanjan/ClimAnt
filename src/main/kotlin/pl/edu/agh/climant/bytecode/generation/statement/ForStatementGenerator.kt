package pl.edu.agh.climant.bytecode.generation.statement

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.domain.AccessModifier
import pl.edu.agh.climant.domain.classmembers.LocalVariable
import pl.edu.agh.climant.domain.statements.expression.LocalVariableReference
import pl.edu.agh.climant.domain.statements.statement.ForStatement

class ForStatementGenerator(val mv: MethodVisitor) {

    fun generate(forStatement: ForStatement) {
        val scope = forStatement.scope
        val scopeGeneratorWithNewScope = StatementGenerator(mv, scope)
        val expressionGeneratorWithNewScope = ExpressionGenerator(mv, scope)
        val iterator = forStatement.iteratorVariable
        val iteratorVariableName = forStatement.iteratorVariableName
        val incrementationSection = Label()
        val decrementationSection = Label()
        val endLoopSection = Label()
        val endExpression = forStatement.endExpression
        val variable = LocalVariable(AccessModifier.PUBLIC, iteratorVariableName, forStatement.getType())
        val iteratorVariable = LocalVariableReference(variable, variable.getName(), variable.getType())
    }

}