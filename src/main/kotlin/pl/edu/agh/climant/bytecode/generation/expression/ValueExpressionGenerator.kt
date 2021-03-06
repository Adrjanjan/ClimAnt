package pl.edu.agh.climant.bytecode.generation.expression

import org.objectweb.asm.MethodVisitor
import pl.edu.agh.climant.domain.statements.expression.Value
import pl.edu.agh.climant.util.getValueFromString

class ValueExpressionGenerator(private val mv: MethodVisitor) {

    fun generate(value: Value) = mv.visitLdcInsn(
        getValueFromString(
            value.value,
            value.type
        )
    )

}
