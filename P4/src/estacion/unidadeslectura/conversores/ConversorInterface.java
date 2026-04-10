package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.UnidadLectura;

public interface ConversorInterface {
    ConversorInterface getConversor();
    UnidadLectura getVariableOrigen();
    UnidadLectura getVariableDestino();
    double convertirUnidades(double valor);
}
