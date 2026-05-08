package ArbolesDecision.DecisionTree.ConcreteVisitors;

import ArbolesDecision.DecisionTree.DecisionTree;
import ArbolesDecision.DecisionTree.Visitor;

public class IndentedTreeVisitor<T extends Comparable<? super T>> implements Visitor<T>{

    @Override
    public void visit(DecisionTree<T> decisionTree) {
        visitNodeLevel("", decisionTree);
    }

    private void visitNodeLevel(String identation, DecisionTree<T> decisionTree){
        System.out.println(identation + decisionTree.getNodeName());
        for(DecisionTree<T> child : decisionTree.getChildren()){
            visitNodeLevel(identation+"    ", child);
        }
    }
    
}
