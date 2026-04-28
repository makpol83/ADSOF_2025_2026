package ArbolesDecision.Exceptions;

public class StuckElementException extends Exception {
    private Object elementStucked;
    
    public StuckElementException(Object e){
        super(  "Un elemento no ha cumplido ninguna condición de salida del nodo," +
                "se ha quedado atrapado en un nodo intermedio. Objeto atrapado: " + 
                e.toString()
        );
        this.elementStucked = e;
    }

    public Object getElementSuck(){ return this.elementStucked;}
}
