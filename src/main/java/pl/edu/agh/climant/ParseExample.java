package pl.edu.agh.climant;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class ParseExample {

    public static void main(String[] args) throws IOException {

        CharStream charStream = CharStreams.fromFileName("./src/main/antlr/Erlang.g4");
        ErlangLexer erlangLexer = new ErlangLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(erlangLexer);
        ErlangParser erlangParser = new ErlangParser(commonTokenStream);

        ParseTree parseTree = erlangParser.forms();

        System.out.println("done");
    }

}
