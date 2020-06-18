package pl.edu.agh.climant.exceptions

import java.lang.RuntimeException

class UnsupportedForLoopTypes : Throwable("Only integer types are supported for loop iterators")