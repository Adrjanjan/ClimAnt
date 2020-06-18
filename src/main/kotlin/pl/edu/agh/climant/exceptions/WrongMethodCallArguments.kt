package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.statements.expression.Call

class WrongMethodCallArguments(call: Call)
    : Throwable("Method called with wrong arguments: $call")