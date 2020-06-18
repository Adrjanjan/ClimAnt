package pl.edu.agh.climant.visitor.expression

import pl.edu.agh.climant.ClimAntBaseVisitor
import pl.edu.agh.climant.ClimAntParser
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.*

class ExpressionVisitor(private val scope: Scope) : ClimAntBaseVisitor<Expression>() {

    private var arithmeticExpressionVisitor = ArithmeticExpressionVisitor(this)
    private var variableReferenceExpressionVisitor = VariableReferenceExpressionVisitor(scope)
    private var valueExpressionVisitor = ValueExpressionVisitor()
    private var callExpressionVisitor = CallExpressionVisitor(this, scope)
    private var conditionalExpressionVisitor = ConditionalExpressionVisitor(this)

    fun visit(ctx: ClimAntParser.VarReferenceContext): LocalVariableReference {
        return variableReferenceExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.ValueContext): Value {
        return valueExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.FunctionCallContext): MethodCall {
        return callExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.ConstructorCallContext): ConstructorCall {
        return callExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.MultiplyContext): Expression {
        return arithmeticExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.SubstractContext): Expression {
        return arithmeticExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.ConditionalExpressionContext): ConditionalExpression {
        return conditionalExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.DivideContext): Expression {
        return arithmeticExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.PowerContext): Expression {
        return arithmeticExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.AddContext): Expression {
        return arithmeticExpressionVisitor.visit(ctx)
    }

    fun visit(ctx: ClimAntParser.ModuloContext?): Expression {
        return arithmeticExpressionVisitor.visit(ctx)
    }

}
