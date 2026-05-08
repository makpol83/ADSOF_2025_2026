package ArbolesDecision.DecisionTree;

import java.util.Collection;


/**
 * Esta interfaz declara los métodos necesarios para que un árbol sea visitable
 */
public interface VisitableTree {
	/**
	 * Acepta un visitante permitiendo que visite al árbol
	 * @param visitor visitante que visitará el árbol
	 */
    void accept(Visitor visitor);
    
    
    /**
     * Retorna el nombre de este nodo
     */
    String getNodeName();
    
    /**
     * Retorna una colección con los hijos de este nodo en el órden en que fueron añadidos
     */
    Collection<VisitableTree> getChildren();
}
