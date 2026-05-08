package ArbolesDecision.DecisionTree;


/**
 * Interfaz de un visitante 
 */
public interface Visitor {
	
	/**
	 * Visita un árbol visitable y realiza las operaciones que considere necesarias sobre él.
	 * @param tree árbol a visitar
	 */
    void visit(VisitableTree tree);
}
