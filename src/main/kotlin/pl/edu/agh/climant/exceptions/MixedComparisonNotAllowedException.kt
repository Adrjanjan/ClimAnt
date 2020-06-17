package pl.edu.agh.climant.exceptions

import pl.edu.agh.climant.domain.types.Type

class MixedComparisonNotAllowedException(leftType: Type, rightType: Type) :
    Throwable("Comparison between object and primitive is not supported: $leftType | $rightType" )