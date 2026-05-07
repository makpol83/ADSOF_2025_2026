package ArbolesDecision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Predicate;

import ArbolesDecision.Exceptions.StuckElementException;

public class DecisionTree<T extends Comparable<? super T>> {
    private String name;
    private Predicate<T> toNode;
    private List<DecisionTree<T>> children = new ArrayList<>();

    private DecisionTree<T> defaultChild = null;

    public DecisionTree(){
        this.name = null;
    }

    private DecisionTree(String name){
        this.name = name;
    }
    
    public DecisionTree<T> node(String nodeName){

        //Es root si es el primero
        if(this.name == null){
            this.name = nodeName;
            return this;
        }

        return searchForNode(nodeName);
    }

    private DecisionTree<T> searchForNode(String nodeName){
        if(nodeName.equals(name) == true)
            return this;

        for(DecisionTree<T> tree : this.children){
            DecisionTree<T> searchedTree = tree.searchForNode(nodeName);
            if(searchedTree != null)
                return searchedTree;
        }

        return null;
    }

    private List<Predicate<T>> search(String nodeName){
        if(nodeName.equals(name) == true){
            List<Predicate<T>> predicates = new ArrayList<>();
            predicates.add(this.toNode);
            return predicates;
        }

        for(DecisionTree<T> tree : this.children){
            List<Predicate<T>> predicates = search(nodeName);
            if(predicates != null){
                predicates.add(this.toNode);
                return predicates;
            }
        }

        return null;
    }

    public DecisionTree<T> withCondition(String nodeName, Predicate<T> toNode){
        DecisionTree<T> children = new DecisionTree<>(nodeName);
        this.children.add(children);
        children.toNode = toNode;

        //Sigue retornando al padre para añadir más condiciones
        return this;
    }

    public void otherwise(String nodeName){
        if(defaultChild != null)
            return;

        DecisionTree<T> child = new DecisionTree<>(nodeName);
        this.children.add(child);
        this.defaultChild = child;
    }

    public Map<String, Collection<T>> predict(Dataset<T> data){
        return predict(data.getData());
    }

    public Map<String, Collection<T>> predict(T... elementsToEvaluate){
        return predict(List.of(elementsToEvaluate));
    }

    public Map<String, Collection<T>> predict(Collection<T> elementsToEvaluate){
        HashMap<String, Collection<T>> result = new HashMap<>();
        List<T> stuckElements = new ArrayList<>();

        
        for(T t : elementsToEvaluate){
            try{
                String nodeName = this.evaluate(t);
                result.putIfAbsent(nodeName, new ArrayList<>());
                result.get(nodeName).add(t);
            } catch (StuckElementException e){
                stuckElements.add(t);
                result.putIfAbsent(e.getNodeNameStuck(), new ArrayList<>());
                result.get(e.getNodeNameStuck()).add(t);
            }
        }

        if(!stuckElements.isEmpty()){
            System.out.println("Elementos Atrapados en nodos intermedios:");
            System.out.println(stuckElements);
        }

        return result;
    }

    private String evaluate(T e) throws StuckElementException{
        if(this.children.size() == 0)
            return this.name;

        for(DecisionTree<T> t : this.children){
            if(t.toNode == null)
                continue;

            if(t.toNode.test(e) == true){
                return t.evaluate(e);
            }
        }
        if(this.defaultChild != null){
            return defaultChild.evaluate(e);
        } else
            throw new StuckElementException(e, this.name); 
    }
    
    public Predicate<T> getPredicate(String leave_node){
        List<Predicate<T>> predicates = this.search(leave_node);

        if(predicates == null)
            return null;

        Predicate<T> superPredicate = predicates.get(0);
        for(int i = 1; i < predicates.size(); i++){
            superPredicate = superPredicate.and(predicates.get(i));
        }

        return superPredicate;
    }
}