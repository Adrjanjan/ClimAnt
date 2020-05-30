package pl.edu.agh.climant.bytecode.generation

import org.objectweb.asm.ClassWriter
import pl.edu.agh.climant.domain.members.Field

class FieldGenerator(private val classWriter: ClassWriter) {

    fun generate(field: Field) {
        val fieldVisitor = classWriter.visitField(
                field.getAccessModifier().value,
                field.getName(),
                field.getType().getDescriptor(),
                null,
                null
        )
        fieldVisitor.visitEnd()
    }

}