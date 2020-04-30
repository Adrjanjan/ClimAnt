package pl.edu.agh.climant.exceptions

enum class Errors(val message: String) {
    NO_SUCH_FILE("No file is specified for compilation"),
    WRONG_EXTENSION("File has  to end wit .cant extension");
}