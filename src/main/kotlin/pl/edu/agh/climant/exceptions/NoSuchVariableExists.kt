package pl.edu.agh.climant.exceptions

class NoSuchVariableExists(varName: String) : Throwable("Variable $varName does not exist in this context")