package io.github.enzokpl.edgechain;

/**
 * Represents a linear chain of objects connected by explicit relationships.
 * <p>
 * This structure allows storing data not only in the nodes but also in the edges
 * connecting them, making it ideal for rule engines, routes, or logical sequences.
 * </p>
 *
 * @param <T> The type of data in the nodes.
 * @param <R> The type of data in the edges (relations).
 */
public interface Chain<T, R> extends Iterable<Chain.Node<T, R>> {

    /**
     * Initializes the chain with the starting node.
     * This method must be called before adding any subsequent nodes.
     *
     * @param data The object to start the chain with.
     * @throws IllegalStateException if the chain is not empty (already started).
     */
    void addFirst(T data);

    /**
     * Appends a new node to the end of the chain, defining its relationship
     * with the current last node.
     *
     * @param relation The relationship (edge) from the current tail to this new node.
     * @param data     The new object to be added as the new tail.
     * @throws IllegalStateException if the chain is empty (use {@link #addFirst(T)} first).
     */
    void addNext(R relation, T data);

    /**
     * Checks if the chain contains no nodes.
     *
     * @return true if the chain is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of nodes currently in the chain.
     *
     * @return the integer count of nodes.
     */
    int size();

    /**
     * Validates the entire chain using a provided strategy.
     * Uses short-circuit logic: stops at the first failure.
     *
     * @param validator The validation logic to apply to each link.
     * @return true if all links satisfy the validator, false otherwise.
     */
    boolean isValid(ChainValidator<T, R> validator);

    /**
     * Represents a readable node in the chain.
     * Exposes data and navigation without allowing modification of the structure.
     */
    interface Node<T, R> {

        /**
         * Retrieves the data stored in this node.
         *
         * @return the data of type T.
         */
        T getData();

        /**
         * Retrieves the relationship outgoing from this node to the next one.
         *
         * @return the relation of type R, or null if this is the last node.
         */
        R getRelationToNext();

        /**
         * Checks if there is a subsequent node in the chain.
         *
         * @return true if this is not the last node.
         */
        boolean hasNext();

        /**
         * A convenience method to peek at the data of the next node.
         *
         * @return the data of the next node, or null if there is no next node.
         */
        T getNextData();
    }
}