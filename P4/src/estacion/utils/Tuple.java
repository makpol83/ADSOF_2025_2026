package estacion.utils;

public class Tuple<A, B> {
    private final A e1;
    private final B e2;

    public Tuple(A e1, B e2){
        this.e1 = e1;
        this.e2 = e2;
    }

    public A getElement1(){ return this.e1; }
    public B getElement2(){ return this.e2; }

    @Override
    public String toString(){
        return this.e1.toString();
    }
}
