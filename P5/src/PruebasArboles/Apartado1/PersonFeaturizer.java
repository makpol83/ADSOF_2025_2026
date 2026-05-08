package PruebasArboles.Apartado1;

import java.util.List;

import ArbolesDecision.Features.Feature;
import ArbolesDecision.Features.Featurizer;

public class PersonFeaturizer implements Featurizer<Person>{
    private static List<String> featureNames = List.of("age", "weight", "gender");

    @Override
    public List<String> getFeatureNames() {
        return featureNames;
    }

    @Override
    public <S extends Comparable<? super S>> Feature<S> featurize(Person element, String featureName) {

        switch(featureName){
            case "age":
                Feature<Integer> ageFeature = new Feature<>(featureNames.get(0));
                ageFeature.add(element.getAge());
                return (Feature)ageFeature;
            case "weight":
                Feature<Double> weightFeature = new Feature<>(featureNames.get(1));
                weightFeature.add(element.getWeight());
                return (Feature)weightFeature;
            case "gender":
                Feature<Gender> genderFeature = new Feature<>(featureNames.get(2));
                if(element.isMale() == true){
                    genderFeature.add(Gender.MALE);
                } else {
                    genderFeature.add(Gender.FEMALE);
                }
                return (Feature)genderFeature;

        }

        return null;
    }
}
