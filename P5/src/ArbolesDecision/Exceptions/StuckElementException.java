package ArbolesDecision.Exceptions;


/**
 * Excepción que debe ser lanzada cuando un elemento no llega a un nodo hoja de un árbol de decisión tras ser evaluado
 */
public class StuckElementException extends Exception {
	/** Elemento que se ha quedado atascado */
    private Object elementStucked;
    /** Nombre del nodo en que el elemento se ha quedado atascado */
    private String nodeNameStucked;
    
    /**
     * Construye y retorna una excepción dado un elemento y el nombre de un nodo
     * @param e elemento que se ha atascado
     * @param nodeNameStucked nodo en el que se ha atascado el elemento
     */
    public StuckElementException(Object e, String nodeNameStucked){
        super(  "Un elemento no ha cumplido ninguna condición de salida del nodo," +
                "se ha quedado atrapado en un nodo intermedio. Objeto atrapado: " + 
                e.toString()
        );
        this.elementStucked = e;
        this.nodeNameStucked = nodeNameStucked;
    }

    /**
     * Retorna el elemento atascado
     * @return elemento atascado
     */
    public Object getElementStuck(){ return this.elementStucked;}
    
    /**
     * Retorna el nombre del nodo en el que se atascó un elemento
     * @return nombre del nodo
     */
    public String getNodeNameStuck(){ return this.nodeNameStucked;}
}
