package io.github.enzokpl.edgechain;

/**
 * Represents a linear chain of objects connected by explicit relationships.
 * @param <T> The type of data in the nodes.
 * @param <R> The type of data in the edges (relations).
 */
public interface Chain<T, R> extends Iterable<Chain.Node<T, R>> {

    void addFirst(T data);

    void addNext(R relation, T data);

    boolean isEmpty();
    
    int size();

    /**
     * Validates the entire chain using a provided strategy.
     * Uses short-circuit logic: stops at the first failure.
     *
     * @param validator The validation logic.
     * @return true if all links satisfy the validator, false otherwise.
     */
    boolean isValid(ChainValidator<T, R> validator);

    /**
     * Represents a readable node in the chain.
     */
    interface Node<T, R> {
        T getData();
        R getRelationToNext();
        boolean hasNext();
        T getNextData(); 
    }
}