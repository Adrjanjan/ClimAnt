package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.Statement

class BlockStatementVisitor(scope: Scope) {
    fun visit(ctx: ClimAntParser.BlockContext): Statement? {
        TODO("Not yet implemented")
    }

}
