package ArbolesDecision.DecisionTree.ConcreteVisitors;

import ArbolesDecision.DecisionTree.DecisionTree;
import ArbolesDecision.DecisionTree.VisitableTree;
import ArbolesDecision.DecisionTree.Visitor;

public class IndentedTreeVisitor implements Visitor{

    @Override
    public void visit(VisitableTree decisionTree) {
        visitNodeLevel("", decisionTree);
    }

    private void visitNodeLevel(String identation, VisitableTree decisionTree){
        System.out.println(identation + decisionTree.getNodeName());
        for(VisitableTree child : decisionTree.getChildren()){
            visitNodeLevel(identation+"    ", child);
        }
    }
    
}
