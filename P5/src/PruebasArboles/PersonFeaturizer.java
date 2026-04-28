package PruebasArboles;

import java.util.List;

import ArbolesDecision.Features.Feature;
import ArbolesDecision.Features.Featurizer;

public class PersonFeaturizer implements Featurizer<Person>{

    @Override
    public List<String> getFeatureNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFeatureNames'");
    }

    @Override
    public boolean featurize(Feature<?> featureToAdd, String featureName, Person element) {
        switch(featureName){
            case "age":
                featureToAdd.add(new Feature<Integer>(featureName, List.of(element.getAge())));
        }
    }

    
}
