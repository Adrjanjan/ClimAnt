package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope

class ParameterExpressionGenerator(private val mv: MethodVisitor,
                                   private val scope: Scope) {

    fun generate(parameter: Parameter) {
        val type = parameter.type
        val index = scope.getLocalVariableIndex(parameter.name)
        mv.visitVarInsn(type.getLoadVariableOpcode(), index)
    }

}