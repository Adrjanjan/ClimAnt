package pl.edu.agh.climant.visitor.statement

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.Statement
import pl.edu.agh.climant.domain.statements.statement.Block

class BlockStatementVisitor(private val scope: Scope) : ClimAntBaseVisitor<Block>() {

    fun visit(ctx: ClimAntParser.BlockContext): Block {
        val statementsCtx = ctx.statement()
        val scope = Scope(this.scope)
        val visitor = StatementVisitor(scope)
        val statements = statementsCtx.map {it.accept(visitor)}
        return Block(scope, statements)
    }

}
