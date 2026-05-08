package PruebasArboles.Apartado4;

import ArbolesDecision.*;
import ArbolesDecision.Datasets.LabeledDataSet;
import ArbolesDecision.DecisionTree.DecisionTree;
public class WeatherGreedyTree {
    public static void main(String[] args){
        DecisionTree<Weather> learnTree = learnTree();
        learnTree.print();
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
