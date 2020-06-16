package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.classmembers.Scope

class ConstructorNotFoundException(scope: Scope, className: String, arguments: List<Parameter>) : Throwable(
    "Constructor for class $className with parameters ${arguments.joinToString(
        ", ",
        "(",
        ")",
        transform = { it.type.getTypeName()!! })} was found"
)