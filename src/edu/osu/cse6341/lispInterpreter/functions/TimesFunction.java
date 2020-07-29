package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.valueretriver.NumericValueRetriever;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class TimesFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NumericValueRetriever numericValueRetriever;
    private final NodeGenerator nodeGenerator;

    @Override
    public LispNode evaluateLispFunction(final LispNode params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.TIMES,
            FunctionLengthConstants.THREE,
            params.parameterLength()
        );
        LispNode evaluatedAddress = ((IEvaluatable)params).evaluate(true);
        int leftValue = numericValueRetriever.retrieveNumericValue(
            evaluatedAddress,
            1,
            FunctionNameConstants.TIMES
        );
        LispNode right = ((ExpressionNode) params).getData();
        LispNode evaluatedData = ((IEvaluatable)right).evaluate(true);
        int rightValue = numericValueRetriever.retrieveNumericValue(
            evaluatedData,
            2,
            FunctionNameConstants.TIMES
        );
        int result = leftValue * rightValue;
        return nodeGenerator.generateAtomNode(result);
    }
}