package estacion.medidas;

public enum Temperatura {
    Celsius(-273.15, 1000),
    Fahrenheit(0,1),
    Kelvin(0, 1273.15);

    private double minTemp;
    private double maxTemp;

    Temperatura(double minTemp, double maxTemp){
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public double getValorMinimo() { return this.minTemp; }

    public double getValorMaximo() { return this.maxTemp; }

    /*public static double cambiarMedida(double temperatura, 
        MedidaTemperatura medidaActual, MedidaTemperatura medidaNueva)
    {
        if(medidaActual.equals(medidaNueva) == true)
            return temperatura;

        switch(medidaActual){
            case Celsius:
                if(medidaNueva.equals(MedidaTemperatura.Fahrenheit) == true){
                    // Celsius a Fahrenheit
                    return temperatura * 1.8 + 32;
                } else {
                    // Celsius a Kelvin
                    return temperatura + 273.15;
                }
            case Fahrenheit:
                double tempTemporal1 = (temperatura - 32) / 1.8;
                if(medidaNueva.equals(MedidaTemperatura.Celsius) == true){
                    // Fahrenheit a Celsius
                    return tempTemporal1;
                } else {
                    // Fahrenheit a Kelvin
                    return tempTemporal1 + 273.15;
                }
            case Kelvin:
                double tempTemporal2 = temperatura - 273.15;
                if(medidaNueva.equals(MedidaTemperatura.Celsius) == true){
                    // Fahrenheit a Celsius
                    return tempTemporal2;
                } else {
                    // Fahrenheit a Kelvin
                    return tempTemporal2 * 1.8 + 32;
                }
            default:
                return temperatura;
        }
    }*/
}
