package ArbolesDecision.DecisionTree.ConcreteVisitors;

import ArbolesDecision.DecisionTree.VisitableTree;
import ArbolesDecision.DecisionTree.Visitor;

/**
 * Esta clase implementa la funcionalidad para imprimir un árbol en el formato DOT
 */
public class GraphVizVisitor implements Visitor{

    /**
     * Imprime por pantalla el árbol visitable en formato DOT
     */
	@Override
    public void visit(VisitableTree tree) {
        String finalString = "digraph G {\n" + getNodeLinkage(tree) + attributes(tree) + "}";
        System.out.println(finalString);
    }

    
	/**
	 * Retorna una cadena que asigna la forma Mdiamond al nodo del árbol especificado en formato DOT
	 * @param tree nodo al que asignar el atributo de forma
	 * @return cadena que representa la forma
	 */
    private String attributes(VisitableTree tree){
        return "    \"" + tree.getNodeName() + "\"" + " [shape = Mdiamond];\n";
    }

    /**
     * Retorna una cadena que contiene todos los caminos de un árbol en formato DOT
     * @param tree árbol desde el que imprimir
     * @return cadena que contiene el árbol en formato DOT
     */
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