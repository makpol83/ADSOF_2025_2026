package PruebasArboles.Apartado1;

public class Person implements Comparable<Person>{
    private String name;
    private int age;
    private double weight;
    private boolean isMale;

    
    public Person(String name, int age, double weight, double gender, boolean male){
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.isMale = male;
    }

    public String getName(){ return name; }
    public int getAge(){ return age; }
    public double getWeight(){ return weight; }
    public boolean isMale(){ return isMale; }

    @Override
    public int compareTo(Person element){
        if(element.age != this.age)
            return this.age - element.age;

        if(element.weight != this.weight)
            return Double.compare(this.weight, element.weight);
        
        if(element.isMale != this.isMale)
            return 1;

        return 0;
    }
 
    @Override
    public String toString(){
        return this.name +"(age: " + this.age +", " + ((isMale) ? "male" : "female");
    }
}
