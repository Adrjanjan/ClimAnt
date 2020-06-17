package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.FieldReference
import pl.edu.agh.climant.domain.statements.expression.LocalVariableReference

class ReferenceExpressionGenerator(val mv: MethodVisitor,
                                   val scope: Scope) {

    fun generate(localVariableReference: LocalVariableReference) {
        val variableName = localVariableReference.name
        val index = scope.getLocalVariableIndex(variableName)
        val type = localVariableReference.type
        mv.visitVarInsn(type.getLoadVariableOpcode(), index)
    }

    fun generate(fieldReference: FieldReference) {
        val variableName = fieldReference.name
        val type = fieldReference.type
        val ownerInternalName = fieldReference.getOwnerInternalName()
        val descriptor = type.getDescriptor()
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, ownerInternalName, variableName, descriptor)
    }

}