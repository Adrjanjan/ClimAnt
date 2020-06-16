package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.classmembers.Scope

class FieldNotFoundException(scope: Scope, fieldName: String): Throwable (
    "No field $fieldName was found in context ${scope.className}"
)
