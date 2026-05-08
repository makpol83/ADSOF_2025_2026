package ArbolesDecision.DecisionTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import ArbolesDecision.Datasets.Dataset;
import ArbolesDecision.DecisionTree.ConcreteVisitors.IndentedTreeVisitor;
import ArbolesDecision.Exceptions.StuckElementException;

/**
 * Esta clase proporciona la funcionalidad básica para construir un árbol de decisión usando métodos.
 * @param <T> tipo paramétrico del árbol de decisión
 */
public class DecisionTree<T> implements VisitableTree{
	/** Nombre del nodo */
    private String name;
    /** Predicado que determina si un objeto del nodo padre es apto para pasar por este nodo */
    private Predicate<T> toNode;
    /** Lista que contiene a los hijos de este nodo */
    private List<DecisionTree<T>> children = new ArrayList<>();
    /** Nodo hijo al que los objetos irán en caso de darse una condición "otherwise" */
    private DecisionTree<T> defaultChild = null;
    /** Nodo padre. En la raíz vale null */
    private DecisionTree<T> parent = null;

    /**
     * Construye y retorna la raíz de un árbol de decisión
     */
    public DecisionTree(){
        this.name = "root";
        this.parent = null;
    }

    /**
     * Construye y retorna un nodo con nombre que será asignado a la lista de hijos de otro nodo
     * @param name nombre del nodo
     */
    private DecisionTree(String name){
        this.name = name;
    }
    
    /**
     * Busca y retorna, si existe, el nodo o subárbol perteneciente a este 
     * @param nodeName nombre del nodo a buscar
     * @return Nodo buscado o null si no está contenido en este objeto
     */
    public DecisionTree<T> node(String nodeName){

        //Es root si es el primero
        if(this.name == null){
            this.name = nodeName;
            return this;
        }

        return searchForNode(nodeName);
    }
    
    /**
     * Retorna true si este árbol contiene al nodo de nombre especificado. Este método no comprueba
     * desde el nodo raíz, sino desde el nodo llamante para abajo.
     * @param nodeName nombre del nodo especificdado
     * @return true si lo contiene, false si no
     */
    public boolean contains(String nodeName) {
    	return this.searchForNode(nodeName) != null;
    }
    
    
    /**
     * Retorna la raíz de este árbol. Si el método llamante es la raíz, se retorna a sí mismo.
     * @return raíz del árbol.
     */
    public DecisionTree<T> root(){
    	DecisionTree<T> root = this;
    	while(root.parent != null)
    		root = root.parent;
    	return root;
    }

    /**
     * Método auxiliar para buscar un nodo contenido en este árbol
     * @param nodeName nombre del nodo a buscar
     * @return nodo buscado o null si no existe
     */
    private DecisionTree<T> searchForNode(String nodeName){
        if(nodeName.equals(name) == true)
            return this;

        for(DecisionTree<T> tree : this.children){
            DecisionTree<T> searchedTree = tree.searchForNode(nodeName);
            if(searchedTree != null)
                return searchedTree;
        }

        return null;
    }

    /**
     * Retorna una lista con los predicados por los que un objeto del tipo paramétrico de este árbol, ha sido sometido
     * hasta llegar al nodo de nombre especificado
     * @param nodeName nombre del nodo a buscar los predicados de su camino
     * @return Lista con los predicados o null si no se encontró el nodo
     */
    private List<Predicate<T>> search(String nodeName){
        if(nodeName.equals(name) == true){
            List<Predicate<T>> predicates = new ArrayList<>();
            predicates.add(this.toNode);
            return predicates;
        }

        for(DecisionTree<T> tree : this.children){
            List<Predicate<T>> predicates = tree.search(nodeName);
            if(predicates != null){
                if(this.toNode != null)
                    predicates.add(this.toNode);
                return predicates;
            }
        }

        return null;
    }

    /**
     * Crea y añade un nodo a este árbol de decisión con el nombre y predicado para llegar a él especificados.
     * @param nodeName nombre del nodo a añadir
     * @param toNode predicado para llegar al nodo
     * @return El nodo sobre el que se llama a este método (padre del nodo creado) o null si ya existía un nodo
     * con ese nombre
     */
    public DecisionTree<T> withCondition(String nodeName, Predicate<T> toNode){
    	if(this.root().contains(nodeName)) return null;
        DecisionTree<T> children = new DecisionTree<>(nodeName);
        this.children.add(children);
        children.toNode = toNode;
        children.parent = this;

        //Sigue retornando al padre para añadir más condiciones
        return this;
    }

    /**
     * Crea y añade un nodo a este árbol para el que todos los objetos podrán pasar en caso de que cumplan ninguno
     * de los predicados del mismo nivel previos. En caso de que ya existiera un nodo de estas características en 
     * el mismo nivel, se sobreescribirá perdiendo el subárbol que pudiera formar 
     * @param nodeName nombre del nodo a crear
     * @return DecisionTree T árbol de decisión usado
     */
    public DecisionTree<T> otherwise(String nodeName){
    	if(this.root().contains(nodeName)) return null;
    	
    	if(this.defaultChild != null) 
    		this.children.remove(this.defaultChild);
    	
        DecisionTree<T> child = new DecisionTree<>(nodeName);
        this.children.add(child);
        this.defaultChild = child;
        child.parent = this;
        return this;
    }

    
    /**
     * Retorna un mapa que relaciona los nodo hoja con todos los objetos que hayan tomado camino en el árbol de decision
     * que lleva a él. 
     * Además, imprime por pantalla aquellos objetos que no llegaron a un nodo hoja.
     * @param data Dataset con los elementos sobre los que realizar la precición
     * @return mapa que relaciona los nodos con los objetos que llegaron a él
     */
    public Map<String, Collection<T>> predict(Dataset<T> data){
        return predict(data.getData());
    }

    /**
     * Retorna un mapa que relaciona los nodo hoja con todos los objetos que hayan tomado camino en el árbol de decision
     * que lleva a él. 
     * Además, imprime por pantalla aquellos objetos que no llegaron a un nodo hoja.
     * @param elementsToEvaluate array con los elementos sobre los que realizar la precición
     * @return mapa que relaciona los nodos con los objetos que llegaron a él
     */
    public Map<String, Collection<T>> predict(T ... elementsToEvaluate){
        return predict(List.of(elementsToEvaluate));
    }

    /**
     * Retorna un mapa que relaciona los nodo hoja con todos los objetos que hayan tomado camino en el árbol de decision
     * que lleva a él. 
     * Además, imprime por pantalla aquellos objetos que no llegaron a un nodo hoja.
     * @param elementsToEvaluate colección de elementos a evaluar
     * @return mapa que relaciona los nodos con los objetos que llegaron a él
     */
    public Map<String, Collection<T>> predict(Collection<T> elementsToEvaluate){
        HashMap<String, Collection<T>> result = new HashMap<>();
        List<T> stuckElements = new ArrayList<>();

        
        for(T t : elementsToEvaluate){
            try{
                String nodeName = this.evaluate(t);
                result.putIfAbsent(nodeName, new ArrayList<>());
                result.get(nodeName).add(t);
            } catch (StuckElementException e){
                stuckElements.add(t);
                result.putIfAbsent(e.getNodeNameStuck(), new ArrayList<>());
                result.get(e.getNodeNameStuck()).add(t);
            }
        }

        if(!stuckElements.isEmpty()){
            System.out.println("Elementos Atrapados en nodos intermedios:");
            System.out.println(stuckElements);
        }

        return result;
    }

    /**
     * Evalua un elemento recorriendo el árbol por el camino que satisface todos los predicados.
     * En caso de no llegar a un nodo hoja y no cumplir ninguno de los predicados del nodo actual,
     * lanza una excepción
     * @param e elemento a evaluar
     * @return nombre del nodo hoja al que ha llegado (final del camino)
     * @throws StuckElementException en caso de que un elemento quede "atascado" sin llegar al final del camino
     */
    private String evaluate(T e) throws StuckElementException{
        if(this.children.size() == 0)
            return this.name;

        for(DecisionTree<T> t : this.children){
            if(t.toNode == null)
                continue;

            if(t.toNode.test(e) == true){
                return t.evaluate(e);
            }
        }
        if(this.defaultChild != null){
            return defaultChild.evaluate(e);
        } else
            throw new StuckElementException(e, this.name); 
    }
    
    /**
     * Retorna un predicado que pasarán aquellos objetos cuyo camino en el árbol de decisión contiene al nodo especificado
     * @param node nodo para el que obtener el predicado
     * @return el predicado que pasarán los objetos cuyo camino contiene al nodo especificado
     */
    public Predicate<T> getPredicate(String node){
        List<Predicate<T>> predicates = this.search(node);

        if(predicates == null || predicates.isEmpty())
            return null;

        Predicate<T> superPredicate = predicates.get(0);
        for(int i = 1; i < predicates.size(); i++){
            superPredicate = superPredicate.and(predicates.get(i));
        }

        return superPredicate;
    }

    /**
     * Imprime el árbol de decision con el formato especificado por IndentedTreeVisitor
     */
    public void print() {
        this.accept(new IndentedTreeVisitor());
    }

    @Override
    public Collection<VisitableTree> getChildren(){
        return new ArrayList<>(this.children);
    }

    @Override
    public String getNodeName(){
        return this.name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}