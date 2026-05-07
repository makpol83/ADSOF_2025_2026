package PruebasArboles.Apartado4;

import PruebasArboles.Apartado4.*;
import ArbolesDecision.Features.*;
import java.util.List;

public class WeatherFeaturizer implements Featurizer<Weather> {
    private static List<String> featureNames = List.of("weather", "temperature", "court_state");
    
    public List<String> getFeatureNames(){
        return featureNames;
    }
    
    public <S extends Comparable<? super S>> Feature<S> featurize(Weather element, String featureName){
        switch(featureName){
            case "weather":
                Feature<WeatherCondition> weatherFeature = new Feature<>(featureNames.get(0));
                weatherFeature.add(element.getWeatherCondition());
                return (Feature)weatherFeature;
            case "temperature":
                Feature<Temperature> tempFeature = new Feature<>(featureNames.get(1));
                tempFeature.add(element.getTemperature());
                return (Feature)tempFeature;
            case "court_state":
                Feature<CourtStatus> courtStateFeature = new Feature<>(featureNames.get(2));
                courtStateFeature.add(element.getCourtState());
                return (Feature)courtStateFeature;
        }

        return null;
    }
}