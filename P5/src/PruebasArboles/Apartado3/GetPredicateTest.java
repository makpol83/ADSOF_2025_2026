package PruebasArboles.Apartado3;

import ArbolesDecision.DecisionTree.DecisionTree;
import PruebasArboles.Apartado1.Person;
import java.util.function.Predicate;

public class GetPredicateTest {

    public static void main(String[] args){
        DecisionTree<Person> dt = buildPersonDecisionTree();

        dt.print();
        
        Person people[] = {
                            new Person("Ana", 47, 54, 158, false ),
                            new Person("Luis", 34, 75, 176, true ),
                            new Person("Rosa", 47, 54, 158, false )
        };

        Person oldMale = new Person("Pedro", 66, 75, 180, true );

        Predicate<Person> p = dt.getPredicate("old male");

        if(p.test(oldMale) == true){
            System.out.println("Test works fine for an old male");
        }

        for(Person notOldMale : people){
            String personString = "Person to test predicate: " + notOldMale.toString() + ": ";
            if(p.test(notOldMale) == true){
                System.out.println(personString + "error");
            } else{
                System.out.println(personString + "correct"); 
            }
        }
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