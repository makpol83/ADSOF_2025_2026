package ArbolesDecision.DecisionTree.ConcreteVisitors;


import ArbolesDecision.DecisionTree.VisitableTree;
import ArbolesDecision.DecisionTree.Visitor;

/**
 * Esta clase implementa la funcionalidad para imprimir un árbol con un formato de indentación normal
 */
public class IndentedTreeVisitor implements Visitor{

    /** Constructor default */
    public IndentedTreeVisitor(){}

	/**
	 * Imprime el árbol con un formado de indentación tabulada
	 */
    @Override
    public void visit(VisitableTree tree) {
        visitNodeLevel("", tree);
    }

    /**
     * Imprime el nodo y todos sus hijos con un formato de indentación tabulada
     * @param identation intentación a aplicar al nodo actual
     * @param tree árbol a imprimir
     */
    private void visitNodeLevel(String identation, VisitableTree tree){
        System.out.println(identation + tree.getNodeName());
        for(VisitableTree child : tree.getChildren()){
            visitNodeLevel(identation+"    ", child);
        }
    }
    
}
