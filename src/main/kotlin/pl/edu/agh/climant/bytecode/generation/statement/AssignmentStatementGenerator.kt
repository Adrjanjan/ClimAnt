package pl.edu.agh.climant.bytecode.generation.statement

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.bytecode.generation.expression.ExpressionGenerator
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.statement.Assignment
import pl.edu.agh.climant.domain.types.Type

class AssignmentStatementGenerator(private val expressionGenerator: ExpressionGenerator,
                                   private val mv: MethodVisitor,
                                   private val scope: Scope
) {

    fun generate(assignment: Assignment) {
        val variableName = assignment.variableName
        val expression = assignment.expression
        val type = expression.type

        if (scope.localVariableExists(variableName)) {
            val index = scope.getLocalVariableIndex(variableName)
            val localVariable = scope.getLocalVariable(variableName)
            val localVariableType = localVariable.getType()
            castIfNeeded(type, localVariableType)
            mv.visitVarInsn(type.getStoreVariableOpcode(), index)
            return
        }
        val field = scope.getField(variableName)
        val descriptor = field.getType().getDescriptor()
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        expression.accept(expressionGenerator)
        castIfNeeded(type, field.getType())
        mv.visitFieldInsn(Opcodes.PUTFIELD, field.getInternalName(), field.getName(), descriptor)
    }

    private fun castIfNeeded(type: Type, localVariableType: Type) {
        if (type != localVariableType) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, localVariableType.getInternalName())
        }
    }

}