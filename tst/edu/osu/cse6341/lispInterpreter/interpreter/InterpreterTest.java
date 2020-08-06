package edu.osu.cse6341.lispInterpreter.interpreter;

import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.parser.Parser;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.program.Program;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

class InterpreterTest {

    private Scanner in;

    private Tokenizer tokenizer;
    private Parser parser;
    private Program program;
    private ListNotationPrinter listNotationPrinter;

    private Interpreter interpreter;

    @BeforeEach
    void setup() {
        in = new Scanner(System.in);

        tokenizer = Mockito.mock(Tokenizer.class);
        parser = Mockito.mock(Parser.class);
        program = Mockito.mock(Program.class);
        listNotationPrinter = Mockito.mock(ListNotationPrinter.class);

        interpreter = Interpreter.newInstance(
            tokenizer,
            parser,
            program,
            listNotationPrinter
        );
    }

    @Test
    void interpretTest() throws Exception {
        Queue<Token> tokens = new LinkedList<>();
        Mockito.when(tokenizer.tokenize(in)).thenReturn(tokens);

        List<LispNode> rootNodes = Collections.emptyList();
        Mockito.when(parser.parse(tokens)).thenReturn(rootNodes);

        List<LispNode> evaluatedNodes = Collections.emptyList();
        Mockito.when(program.evaluate(evaluatedNodes)).thenReturn(evaluatedNodes);

        String value = "value";
        Mockito.when(listNotationPrinter.printInListNotation(evaluatedNodes)).thenReturn(value);

        String actual = interpreter.interpret(in);

        Assertions.assertEquals(value, actual);
    }
}