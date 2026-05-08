package ArbolesDecision.DecisionTree.ConcreteVisitors;

import ArbolesDecision.DecisionTree.DecisionTree;
import ArbolesDecision.DecisionTree.VisitableTree;
import ArbolesDecision.DecisionTree.Visitor;

public class GraphVizVisitor implements Visitor{

    @Override
    public void visit(VisitableTree tree) {
        String finalString = "digraph G {\n" + getNodeLinkage(tree) + attributes(tree) + "}";
        System.out.println(finalString);
    }

    private String attributes(VisitableTree tree){
        return "    \"" + tree.getNodeName() + "\"" + " [shape = Mdiamond];\n";
    }

    private String getNodeLinkage(VisitableTree tree){
        String output = "";
        for(VisitableTree child : tree.getChildren()){
            output += "    \"" +tree.getNodeName() + "\""+ " -> " + "\"" +child.getNodeName() + "\"" + ";\n";
        }

        for(VisitableTree child : tree.getChildren()){
            output += getNodeLinkage(child);
        }

        if(tree.getChildren().size() == 0){
            output += "    \"" + tree.getNodeName() + "\"" + " [shape=Msquare];\n";
        }

        return output;
    }
    
}