package org.librae.common.util;

public class TemporalKeyGenerator {

    /**
     * counter to generate unique values in tests
     */
    private static int testPKCounter = 1000;

    private static int next() {
        TemporalKeyGenerator.testPKCounter++;
        return TemporalKeyGenerator.testPKCounter;
    }

    /**
     * Returns a unique value
     * 
     * @param pk
     * @return
     */
    public static String unique(final String pk) {
        return pk + next();
    }

}
