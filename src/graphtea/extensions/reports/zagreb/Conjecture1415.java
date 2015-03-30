// GraphTea Project: http://github.com/graphtheorysoftware/GraphTea
// Copyright (C) 2012 Graph Theory Software Foundation: http://GraphTheorySoftware.com
// Copyright (C) 2008 Mathematical Science Department of Sharif University of Technology
// Distributed under the terms of the GNU General Public License (GPL): http://www.gnu.org/licenses/
package graphtea.extensions.reports.zagreb;

import graphtea.graph.graph.RendTable;
import graphtea.graph.graph.Vertex;
import graphtea.platform.lang.CommandAttitude;
import graphtea.platform.parameter.Parameter;
import graphtea.platform.parameter.Parametrizable;
import graphtea.plugins.main.GraphData;
import graphtea.plugins.reports.extension.GraphReportExtension;

import java.util.Vector;

/**
 * @author Ali Rostami

 */

@CommandAttitude(name = "conj1415", abbreviation = "_conj1415")
public class Conjecture1415 implements GraphReportExtension,Parametrizable {
    public String getName() {
        return "ZIndices of Matching Conjecture 1415";
    }

    @Parameter(name = "Starting Value of Alpha", description = "")
    public Double start_alpha = 0.0;

    @Parameter(name = "End Value of Alpha", description = "")
    public Double end_alpha = 10.0;

    @Parameter(name = "Incremental Value", description = "")
    public Double inc = 0.1;

    public String getDescription() {
        return "ZIndices of Matching Conjecture 1415";
    }

    public Object calculate(GraphData gd) {
        ZagrebIndexFunctions zif = new ZagrebIndexFunctions(gd.getGraph());
        RendTable ret = new RendTable();
        ret.add(new Vector<Object>());
        ret.get(0).add("Alpha");
        ret.get(0).add(" M^{a}_2 (M) ");
        ret.get(0).add(" Delta m ");
        ret.get(0).add(" m(n-1) ");
        ret.get(0).add(" 2m^2/n ");
        ret.get(0).add(" 1/2 (2m/n)^a M^a_1(M) ");
        ret.get(0).add(" 1/2 (2m/n)^a M^a_1(G) ");

        double maxDeg = 0;
        for(Vertex v : gd.getGraph()) {
            if(gd.getGraph().getDegree(v) > maxDeg)
                maxDeg = gd.getGraph().getDegree(v);
        }

        int ind = 0;
        for(double alpha = start_alpha;alpha <= end_alpha;alpha=alpha+inc) {
            ind++;
            double fZagrebM = zif.getFirstZagrebSelectedEdges(alpha-1);
            double fZagrebG = zif.getFirstZagreb(alpha - 1);
            double sZagreb = zif.getSecondZagrebSelectedEdges(alpha);
            double mnMinus1 = gd.getGraph().getEdgesCount()
                    *(gd.getGraph().numOfVertices()-1);
            double twomP2On =
                    (Math.pow(gd.getGraph().getEdgesCount(),2)*2)/
                            gd.getGraph().numOfVertices();

            double coef = 2.*gd.getGraph().getEdgesCount()/
                    gd.getGraph().getVerticesCount();
            coef = 1/2.*Math.pow(coef, alpha);
            ret.add(new Vector<Object>());
            ret.get(ind).add(alpha);
            ret.get(ind).add(sZagreb);
            ret.get(ind).add(maxDeg*gd.getGraph().getEdgesCount());
            ret.get(ind).add(mnMinus1);
            ret.get(ind).add(twomP2On);
            ret.get(ind).add(coef*fZagrebM);
            ret.get(ind).add(coef*fZagrebG);
        }
        return ret;
    }

    @Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "Topological Indices";
	}

    @Override
    public String checkParameters() {
        return null;
    }
}
