package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.Argument

class MethodSignatureNotFoundException(scope: Scope, methodName: String, arguments: List<Argument>) : Throwable(
    "No method $methodName was found in context ${scope.className} was found"
)
