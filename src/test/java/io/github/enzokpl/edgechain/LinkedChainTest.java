package io.github.enzokpl.edgechain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.enzokpl.edgechain.Chain;
import io.github.enzokpl.edgechain.LinkedChain;
import org.junit.jupiter.api.Test;

class LinkedChainTest {

    @Test
    void testBasicFlow() {
        Chain<String, String> chain = new LinkedChain<>();
        chain.addFirst("Start");
        chain.addNext("->", "Middle");
        chain.addNext("->", "End");

        assertEquals(3, chain.size());
        assertFalse(chain.isEmpty());
        assertEquals("Start --[->]--> Middle --[->]--> End", chain.toString());
    }

    @Test
    void testValidationLogicSuccess() {
        Chain<Integer, String> chain = new LinkedChain<>();
        // 10 > 5 < 8
        chain.addFirst(10);
        chain.addNext(">", 5);
        chain.addNext("<", 8);

        boolean result = chain.isValid((left, op, right) -> {
            if (op.equals(">")) {
                return left > right;
            }

            if (op.equals("<")) {
                return left < right;
            }
            return false;
        });

        assertTrue(result);
    }

    @Test
    void testValidationLogicFailure() {
        Chain<Integer, String> chain = new LinkedChain<>();
        // 10 > 100 (false)
        chain.addFirst(10);
        chain.addNext(">", 100);

        boolean result = chain.isValid((left, op, right) -> left > right);
        assertFalse(result);
    }
}