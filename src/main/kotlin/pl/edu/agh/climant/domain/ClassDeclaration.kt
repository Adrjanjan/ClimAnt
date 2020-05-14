package pl.edu.agh.climant.domain

import pl.edu.agh.climant.domain.members.Field

class ClassDeclaration(val accessModifier: AccessModifier,
                       val name: String,
                       val fields: Array<Field>,
                       val constructor: Constructor,
                       val methods: Array<Method>) {



}