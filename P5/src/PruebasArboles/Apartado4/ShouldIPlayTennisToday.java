package PruebasArboles.Apartado4;

import ArbolesDecision.LabelProvider;

public class ShouldIPlayTennisToday implements LabelProvider<Weather, Boolean> {

    public Boolean getLabel(Weather element){
        if(element.getTemperature().equals(Temperature.HOT) == true 
            && element.getWeatherCondition().equals(WeatherCondition.SUNNY) == true
            && element.getCourtState().equals(CourtStatus.PERFECT))
            return true;

        return false;
    }
}