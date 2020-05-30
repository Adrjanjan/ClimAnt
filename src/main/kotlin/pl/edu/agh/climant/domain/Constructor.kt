package pl.edu.agh.climant.domain

import pl.edu.agh.climant.bytecode.generation.ConstructorGenerator

class Constructor {

    fun accept(generator: ConstructorGenerator){
        generator.generate(this)
    }

}