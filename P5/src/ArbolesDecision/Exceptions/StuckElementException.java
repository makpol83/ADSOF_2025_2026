package ArbolesDecision.Exceptions;

public class StuckElementException extends Exception {
    private Object elementStucked;
    private String nodeNameStucked;
    
    public StuckElementException(Object e, String nodeNameStucked){
        super(  "Un elemento no ha cumplido ninguna condición de salida del nodo," +
                "se ha quedado atrapado en un nodo intermedio. Objeto atrapado: " + 
                e.toString()
        );
        this.elementStucked = e;
        this.nodeNameStucked = nodeNameStucked;
    }

    public Object getElementStuck(){ return this.elementStucked;}
    public String getNodeNameStuck(){ return this.nodeNameStucked;}
}
