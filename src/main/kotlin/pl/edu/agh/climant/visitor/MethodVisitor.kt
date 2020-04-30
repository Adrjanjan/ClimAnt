package pl.edu.agh.climant.visitor

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.Method

class MethodVisitor : ClimAntBaseVisitor<Method>(){
    @Override
    override fun visitMethod(ctx: ClimAntParser.MethodContext?): Method {
        return super.visitMethod(ctx)
    }
}