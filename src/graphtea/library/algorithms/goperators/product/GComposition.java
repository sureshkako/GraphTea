// GraphTea Project: http://github.com/graphtheorysoftware/GraphTea
// Copyright (C) 2012 Graph Theory Software Foundation: http://GraphTheorySoftware.com
// Copyright (C) 2008 Mathematical Science Department of Sharif University of Technology
// Distributed under the terms of the GNU Lesser General Public License (LGPL): http://www.gnu.org/licenses/

package graphtea.library.algorithms.goperators.product;

import graphtea.library.BaseVertex;

//todo: doc needed (rostam)
public class GComposition extends GProduct {
    public boolean compare(BaseVertex v1OfFirstG, BaseVertex v2OfFirstG, BaseVertex v1OfSecondG, BaseVertex v2OfSecondG) {
        return (g1.isEdge(v1OfFirstG, v2OfFirstG) ||
            (v1OfFirstG== v2OfFirstG
             && g2.isEdge(v1OfSecondG, v2OfSecondG)));

    }
}
