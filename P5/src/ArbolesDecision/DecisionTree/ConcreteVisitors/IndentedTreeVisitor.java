package ArbolesDecision.DecisionTree.ConcreteVisitors;

import ArbolesDecision.DecisionTree.DecisionTree;
import ArbolesDecision.DecisionTree.VisitableTree;
import ArbolesDecision.DecisionTree.Visitor;

public class IndentedTreeVisitor implements Visitor{

    @Override
    public void visit(VisitableTree tree) {
        visitNodeLevel("", tree);
    }

    private void visitNodeLevel(String identation, VisitableTree tree){
        System.out.println(identation + tree.getNodeName());
        for(VisitableTree child : tree.getChildren()){
            visitNodeLevel(identation+"    ", child);
        }
    }
    
}
