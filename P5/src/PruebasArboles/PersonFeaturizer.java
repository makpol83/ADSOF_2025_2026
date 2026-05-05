package PruebasArboles;

import java.util.ArrayList;
import java.util.Collection;
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
    
    public Collection<Feature<?>> featurize(Collection<Person> elements) {
        Collection<Feature<?>> features = new ArrayList<>();
        for(Person person : elements){
            Feature<Integer> ageFeature = new Feature<>(featureNames.get(0));
            ageFeature.add(person.getAge());

            Feature<Double> weightFeature = new Feature<>(featureNames.get(1));
            weightFeature.add(person.getWeight());

            Feature<Gender> genderFeature = new Feature<>(featureNames.get(2));
            if(person.isMale() == true){
                genderFeature.add(Gender.MALE);
            } else {
                genderFeature.add(Gender.FEMALE);
            }

            features.add(weightFeature);
            features.add(genderFeature);
            features.add(ageFeature);
        }

        return features;
    }

    
}
