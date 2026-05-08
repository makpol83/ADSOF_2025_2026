package PruebasArboles.Apartado6;

import ArbolesDecision.*;
import ArbolesDecision.Datasets.LabeledDataSet;
import ArbolesDecision.DecisionTree.DecisionTree;
import ArbolesDecision.DecisionTree.ConcreteVisitors.GraphVizVisitor;
import ArbolesDecision.DecisionTree.ConcreteVisitors.IndentedTreeVisitor;
import ArbolesDecision.Features.*;
import ArbolesDecision.Exceptions.*;
import PruebasArboles.Apartado4.*;
import PruebasArboles.Apartado5.Entropia;
import PruebasArboles.Apartado5.MetricaClasificacionErronea;
import PruebasArboles.*;

public class VisitorTests {
    public static void main(String[] args){
        DecisionTree<Weather> learnTree = learnTreeMetricaClasificacionErronea();
        learnTree.accept(new IndentedTreeVisitor<>());
        learnTree.accept(new GraphVizVisitor<>());

        System.out.println();

        learnTree = learnTreeEntropia();
        learnTree.accept(new GraphVizVisitor<>());
    }

    public static DecisionTree<Weather> learnTreeMetricaClasificacionErronea() {
        LabeledDataSet<Weather, Boolean> dataSet = buildDataSet();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>();
        learner.setFeatureSelectStrategy(new MetricaClasificacionErronea<>());
        DecisionTree<Weather> tree = learner.learn(dataSet);
        return tree;
    }

    public static DecisionTree<Weather> learnTreeEntropia() {
        LabeledDataSet<Weather, Boolean> dataSet = buildDataSet();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>();
        learner.setFeatureSelectStrategy(new Entropia<>());
        DecisionTree<Weather> tree = learner.learn(dataSet);
        return tree;
    }

    private static LabeledDataSet<Weather, Boolean> buildDataSet() {
        Weather conditions[] = {
            new Weather(WeatherCondition.SUNNY, Temperature.HOT, CourtStatus.PERFECT),
            new Weather(WeatherCondition.RAINY, Temperature.COLD, CourtStatus.AWFUL),
            new Weather(WeatherCondition.STORMY, Temperature.BELOW_ZERO, CourtStatus.BAD),
            new Weather(WeatherCondition.DELIGHTFUL, Temperature.TWENTY, CourtStatus.GOOD),
            new Weather(WeatherCondition.NICE, Temperature.TEN, CourtStatus.REGULAR),
            new Weather(WeatherCondition.AWFUL, Temperature.ABOVE_ZERO, CourtStatus.BAD),
            new Weather(WeatherCondition.SUNNY, Temperature.TWENTY, CourtStatus.PERFECT),
            new Weather(WeatherCondition.RAINY, Temperature.TEN, CourtStatus.REGULAR),
            new Weather(WeatherCondition.STORMY, Temperature.HOT, CourtStatus.AWFUL),
            new Weather(WeatherCondition.DELIGHTFUL, Temperature.ABOVE_ZERO, CourtStatus.GOOD),
            new Weather(WeatherCondition.NICE, Temperature.COLD, CourtStatus.REGULAR),
            new Weather(WeatherCondition.SUNNY, Temperature.TEN, CourtStatus.GOOD),
            new Weather(WeatherCondition.AWFUL, Temperature.BELOW_ZERO, CourtStatus.AWFUL),
            new Weather(WeatherCondition.RAINY, Temperature.HOT, CourtStatus.BAD),
            new Weather(WeatherCondition.DELIGHTFUL, Temperature.TWENTY, CourtStatus.PERFECT),
            new Weather(WeatherCondition.STORMY, Temperature.COLD, CourtStatus.REGULAR),
            new Weather(WeatherCondition.NICE, Temperature.ABOVE_ZERO, CourtStatus.GOOD),
            new Weather(WeatherCondition.SUNNY, Temperature.HOT, CourtStatus.REGULAR),
            new Weather(WeatherCondition.RAINY, Temperature.BELOW_ZERO, CourtStatus.AWFUL),
            new Weather(WeatherCondition.AWFUL, Temperature.TEN, CourtStatus.BAD),
            new Weather(WeatherCondition.DELIGHTFUL, Temperature.COLD, CourtStatus.PERFECT),
            new Weather(WeatherCondition.STORMY, Temperature.TWENTY, CourtStatus.REGULAR),
            new Weather(WeatherCondition.NICE, Temperature.HOT, CourtStatus.GOOD),
            new Weather(WeatherCondition.SUNNY, Temperature.BELOW_ZERO, CourtStatus.BAD),
            new Weather(WeatherCondition.RAINY, Temperature.ABOVE_ZERO, CourtStatus.REGULAR),
            new Weather(WeatherCondition.AWFUL, Temperature.TWENTY, CourtStatus.AWFUL),
            new Weather(WeatherCondition.DELIGHTFUL, Temperature.TEN, CourtStatus.GOOD),
            new Weather(WeatherCondition.STORMY, Temperature.HOT, CourtStatus.BAD),
            new Weather(WeatherCondition.NICE, Temperature.COLD, CourtStatus.PERFECT),
            new Weather(WeatherCondition.SUNNY, Temperature.ABOVE_ZERO, CourtStatus.REGULAR),
            new Weather(WeatherCondition.RAINY, Temperature.TWENTY, CourtStatus.GOOD),
            new Weather(WeatherCondition.AWFUL, Temperature.HOT, CourtStatus.BAD),
            new Weather(WeatherCondition.DELIGHTFUL, Temperature.BELOW_ZERO, CourtStatus.AWFUL),
            new Weather(WeatherCondition.STORMY, Temperature.TEN, CourtStatus.REGULAR),
            new Weather(WeatherCondition.NICE, Temperature.ABOVE_ZERO, CourtStatus.PERFECT),
            new Weather(WeatherCondition.SUNNY, Temperature.COLD, CourtStatus.GOOD),
            new Weather(WeatherCondition.RAINY, Temperature.HOT, CourtStatus.REGULAR),
            new Weather(WeatherCondition.AWFUL, Temperature.TEN, CourtStatus.AWFUL),
            new Weather(WeatherCondition.DELIGHTFUL, Temperature.TWENTY, CourtStatus.BAD),
            new Weather(WeatherCondition.STORMY, Temperature.ABOVE_ZERO, CourtStatus.GOOD),
            new Weather(WeatherCondition.NICE, Temperature.BELOW_ZERO, CourtStatus.REGULAR),
            new Weather(WeatherCondition.SUNNY, Temperature.TEN, CourtStatus.PERFECT),
            new Weather(WeatherCondition.RAINY, Temperature.COLD, CourtStatus.BAD),
            new Weather(WeatherCondition.AWFUL, Temperature.ABOVE_ZERO, CourtStatus.REGULAR),
            new Weather(WeatherCondition.DELIGHTFUL, Temperature.HOT, CourtStatus.GOOD),
            new Weather(WeatherCondition.STORMY, Temperature.TWENTY, CourtStatus.AWFUL),
            new Weather(WeatherCondition.NICE, Temperature.TEN, CourtStatus.BAD),
            new Weather(WeatherCondition.SUNNY, Temperature.HOT, CourtStatus.PERFECT),
            new Weather(WeatherCondition.RAINY, Temperature.BELOW_ZERO, CourtStatus.REGULAR),
            new Weather(WeatherCondition.AWFUL, Temperature.TWENTY, CourtStatus.GOOD)
        };

        LabeledDataSet<Weather, Boolean> ds = new LabeledDataSet<>(new WeatherFeaturizer(), new ShouldIPlayTennisToday());
        ds.addAll(conditions);
        return ds;
    }
}
