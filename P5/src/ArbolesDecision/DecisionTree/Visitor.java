package ArbolesDecision.DecisionTree;

public interface Visitor<T extends Comparable<? super T>> {
    void visit(DecisionTree<T> decisionTree);
}
