package ArbolesDecision.DecisionTree;

import java.util.Collection;

public interface VisitableTree {
    void accept(Visitor visitor);
    String getNodeName();
    Collection<VisitableTree> getChildren();
}
