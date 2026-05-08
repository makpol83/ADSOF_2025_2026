package ArbolesDecision.DecisionTree.ConcreteVisitors;

import ArbolesDecision.DecisionTree.DecisionTree;
import ArbolesDecision.DecisionTree.Visitor;

public class GraphVizVisitor<T extends Comparable<? super T>> implements Visitor<T>{

    @Override
    public void visit(DecisionTree<T> decisionTree) {
        String finalString = "digraph G {\n" + getNodeLinkage(decisionTree) + attributes(decisionTree) + "}";
        System.out.println(finalString);
    }

    private String attributes(DecisionTree<T> tree){
        return "    \"" + tree.getNodeName() + "\"" + " [shape = Mdiamond];\n";
    }

    private String getNodeLinkage(DecisionTree<T> tree){
        String output = "";
        for(DecisionTree<T> child : tree.getChildren()){
            output += "    \"" +tree.getNodeName() + "\""+ " -> " + "\"" +child.getNodeName() + "\"" + ";\n";
        }

        for(DecisionTree<T> child : tree.getChildren()){
            output += getNodeLinkage(child);
        }

        if(tree.getChildren().size() == 0){
            output += "    \"" + tree.getNodeName() + "\"" + " [shape=Msquare];\n";
        }

        return output;
    }
    
}