package pl.edu.agh.climant.domain.classmembers

import pl.edu.agh.climant.domain.AccessModifier

data class ClassDeclaration(val accessModifier: AccessModifier,
                       val name: String,
                       val fields: List<Field>,
                       val constructor: Constructor,
                       val methods: List<Method>)