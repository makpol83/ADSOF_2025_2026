package PruebasArboles.Apartado2;

import ArbolesDecision.Dataset;
import ArbolesDecision.DecisionTree;
import PruebasArboles.Apartado1.DatasetTest;
import PruebasArboles.Apartado1.Person;

public class DecisionTreeTest {

    public static void main(String[] args){
        Dataset<Person> dataSet = DatasetTest.buildDataSet();
        DecisionTree<Person> dt = buildPersonDecisionTree();

        System.out.println(dt.predict(dataSet));
        System.out.println(dt.predict(new Person("Miguel", 86, 72, 165, true), new Person("Clara", 42, 59, 162, false)));
    }



    public static DecisionTree<Person> buildPersonDecisionTree(){
        DecisionTree<Person> dt = new DecisionTree<>();
        dt.node("root")
            .withCondition("male", p -> p.isMale())
            .otherwise("female");

        dt.node("male")
            .withCondition("old male", p -> p.getAge() > 65)
            .withCondition("middle male", p -> p.getAge() <= 65 && p.getAge() > 34)
            .otherwise("young male");
            
        return dt;
    }
}