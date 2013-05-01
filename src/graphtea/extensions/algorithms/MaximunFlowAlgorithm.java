package graphtea.extensions.algorithms;

import graphtea.graph.graph.GraphModel;
import graphtea.graph.graph.Vertex;
import graphtea.platform.core.BlackBoard;
import graphtea.plugins.algorithmanimator.core.GraphAlgorithm;
import graphtea.plugins.algorithmanimator.extension.AlgorithmExtension;
import graphtea.plugins.reports.spectralreports.maxflowmincut.PushRelabel;

/**
 * Created with IntelliJ IDEA.
 * User: rostam
 * Date: 5/1/13
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class MaximunFlowAlgorithm extends GraphAlgorithm implements AlgorithmExtension {
    public MaximunFlowAlgorithm(BlackBoard blackBoard) {
        super(blackBoard);
    }

    @Override
    public void doAlgorithm() {
        step("Start of the algorithm.") ;

        GraphModel g = graphData.getGraph();
        Vertex v1 = requestVertex(g, "select the first vertex");
        Vertex v2 = requestVertex(g, "select the second vertex");

        PushRelabel prl = new PushRelabel(g,v1,v2,true);
        prl.perform();

        step("end of the algorithm");

    }

    @Override
    public String getName() {
        return "Maximum Flow";
    }

    @Override
    public String getDescription() {
        return "Generates the maximum flow between two selected nodes.";
    }
}
