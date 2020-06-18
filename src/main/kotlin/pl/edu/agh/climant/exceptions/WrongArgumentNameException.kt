package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.classmembers.Parameter
import pl.edu.agh.climant.domain.statements.expression.Argument

class WrongArgumentNameException(argument: Argument,
                                 parameters: List<Parameter>
) : Throwable("Trying to call method with argument name ${argument.parameterName}, but parameters are $parameters")