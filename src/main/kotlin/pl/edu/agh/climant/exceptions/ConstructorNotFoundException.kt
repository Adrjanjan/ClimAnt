package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope
import pl.edu.agh.climant.domain.statements.expression.Argument

class ConstructorNotFoundException(scope: Scope, className: String, arguments: List<Argument>) : Throwable(
    "Constructor for class $className with parameters ${arguments.joinToString(
        ", ",
        "(",
        ")",
        transform = { it.type.getTypeName()!! })} was found"
)