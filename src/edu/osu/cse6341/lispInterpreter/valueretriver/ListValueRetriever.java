package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ListValueRetriever {

    private final NodeValueComparator nodeValueComparator;

    public ExpressionNode retrieveListValue(
        final LispNode node,
        final String functionName
    ) throws Exception{
        boolean isVariableList = false;
        String temp = node.getNodeValue();
        Environment e = Environment.getEnvironment();
        boolean isVariable = e.isVariableName(temp);
        if(isVariable) isVariableList = e.getVariableValue(temp).isNodeList();
        if((!isVariable && !node.isNodeList() && node.parameterLength() == 1) || (isVariable && !isVariableList) || (!node.isNodeList() && !nodeValueComparator.equalsNil(node.getNodeValue()))) {
            String sb = "Error! Parameter of " + functionName +
                " is not a list.    Actual: " +
                node.getNodeValue() +
                '\n';
            throw new NotAListException(sb);
        }
        LispNode result = isVariableList ? e.getVariableValue(temp) : node;
        return (ExpressionNode)result;
    }
}