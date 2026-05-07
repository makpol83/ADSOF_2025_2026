package PruebasArboles.Apartado4;

public class Weather implements Comparable<Weather>{
    private WeatherCondition condition;
    private Temperature temp;
    private CourtStatus courtState;
    
    public Weather(WeatherCondition condition, Temperature temp, CourtStatus courtState){
        this.condition = condition;
        this.temp = temp;
        this.courtState = courtState;
    }

    public WeatherCondition getWeatherCondition(){
        return this.condition;
    }

    public Temperature getTemperature(){
        return this.temp;
    }

    public CourtStatus getCourtState(){
        return this.courtState;
    }

    @Override
    public int compareTo(Weather weatherNew){
        if(this.condition.equals(weatherNew.condition) != true)
            return this.condition.compareTo(weatherNew.condition);

        if(this.temp.equals(weatherNew.temp) != true)
            return this.temp.compareTo(weatherNew.temp);

        if(this.courtState.equals(weatherNew.courtState) != true)
            return this.courtState.compareTo(weatherNew.courtState);

        return 0;
    }
}
