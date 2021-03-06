package com.soapdogg.lispInterpreter.interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.soapdogg.lispInterpreter.datamodels.PartitionedRootNodes;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator;
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator;
import com.soapdogg.lispInterpreter.parser.Parser;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter;
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer;
import com.soapdogg.lispInterpreter.datamodels.Token;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public final class Interpreter{

	private final Tokenizer tokenizer;
	private final Parser parser;
	private final ProgramEvaluator program;
	private final RootNodePartitioner rootNodePartitioner;
	private final UserDefinedFunctionGenerator userDefinedFunctionGenerator;
	private final ListNotationPrinter listNotationPrinter;

	public String interpret(Scanner in) throws Exception{
	    Queue<Token> tokens = tokenizer.tokenize(in);
	    List<Node> rootNodes = parser.parse(tokens);
		PartitionedRootNodes partitionedRootNodes = rootNodePartitioner.partitionRootNodes(
			rootNodes
		);
		List<UserDefinedFunction> userDefinedFunctions = userDefinedFunctionGenerator.generateUserDefinedFunctions(
			partitionedRootNodes.getDefunNodes()
		);
		List<Node> evaluatedNodes = program.evaluate(
			partitionedRootNodes.getEvaluatableNodes(),
			userDefinedFunctions,
			new HashMap<>()
		);
		return listNotationPrinter.printInListNotation(evaluatedNodes);
    }
}

 
