package pl.edu.agh.climant.domain.statements.expression

import pl.edu.agh.climant.domain.classmembers.Parameter

interface Call : Expression{
    val identifier: String
    fun getArguments(): List<Parameter>
}