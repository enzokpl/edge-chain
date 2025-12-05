package io.github.enzokpl.edgechain;

/**
 * Functional interface to validate or process the relationship between two nodes.
 * @param <T> The type of the Node data.
 * @param <R> The type of the Edge/Relation.
 */
@FunctionalInterface
public interface ChainValidator<T, R> {
    /**
     * Evaluates logic based on the left node, the relation, and the right node.
     * @param left The previous node value.
     * @param relation The relation connecting them.
     * @param right The current node value.
     * @return true if the condition is met, false otherwise.
     */
    boolean validate(T left, R relation, T right);
}