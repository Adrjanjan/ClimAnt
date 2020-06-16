package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope

class MethodSignatureNotFoundException(scope: Scope, methodName: String, arguments: List<Parameter>) : Throwable(
    "No method $methodName was found in context ${scope.className} was found"
)
