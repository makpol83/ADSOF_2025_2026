package PruebasArboles.Apartado4;

import ArbolesDecision.*;
import ArbolesDecision.Features.*;
import ArbolesDecision.Exceptions.*;
import PruebasArboles.Apartado4.*;
import PruebasArboles.*;

public class WeatherGreedyTree {
    public static void main(String[] args){
        DecisionTree<Weather> learnTree = learnTree();
        System.out.print(learnTree);
    }

    public static DecisionTree<Weather> learnTree() {
        LabeledDataSet<Weather, Boolean> dataSet = buildDataSet();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>();
        DecisionTree<Weather> tree = learner. learn(dataSet);
        return tree;
    }

    private static LabeledDataSet<Weather, Boolean> buildDataSet() {
        Weather conditions [] = {
        new Weather(WeatherCondition.RAINY, Temperature.COLD, CourtStatus.GOOD),
        new Weather(WeatherCondition.RAINY, Temperature.HOT, CourtStatus.GOOD),
        new Weather(WeatherCondition.SUNNY, Temperature.HOT, CourtStatus.GOOD),
        new Weather(WeatherCondition.SUNNY, Temperature.COLD, CourtStatus.GOOD)
        };

        LabeledDataSet<Weather, Boolean> ds = new LabeledDataSet<>(new WeatherFeaturizer(), new ShouldIPlayTennisToday());
        ds.addAll(conditions);
        return ds;
    }
}
