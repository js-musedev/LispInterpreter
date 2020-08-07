package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class EqFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;
    private final AtomicValueRetriever atomicValueRetriever;
    private final ListValueRetriever listValueRetriever;
    private final NodeGenerator nodeGenerator;

    @Override
    public Node evaluateLispFunction(
        final Node params,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, Node> variableNameToValueMap
    ) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.EQ,
            FunctionLengthConstants.THREE,
            params
        );
        Node evaluatedAddress = nodeEvaluator.evaluate(
            params,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        );
        String leftValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.EQ
        );
        ExpressionNode expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.EQ,
            variableNameToValueMap
        );
        Node data = expressionNodeParams.getData();
        Node evaluatedData = nodeEvaluator.evaluate(
            data,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        );
        String rightValue = atomicValueRetriever.retrieveAtomicValue(
            evaluatedData,
            2,
            FunctionNameConstants.EQ
        );
        boolean result = leftValue.equals(rightValue);
        return nodeGenerator.generateAtomNode(result);
    }
}
