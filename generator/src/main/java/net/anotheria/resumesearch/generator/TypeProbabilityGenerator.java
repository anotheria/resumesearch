package net.anotheria.resumesearch.generator;

import java.util.Map;
import java.util.Random;

public final class TypeProbabilityGenerator {

    private static TypeProbabilityGenerator INSTANCE;
    private Random r;

    private TypeProbabilityGenerator() {
        r = new Random();
    }

    /**
     * @return TypeProbabilityGenerator instance
     */
    public static TypeProbabilityGenerator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TypeProbabilityGenerator();
        }
        return INSTANCE;
    }

    /**
     * @param classes Map where Key is the Class and Value is Integer that represents probability of choosing current class.
     *                <p>Example: Class1 75, Class2 25 -> Class1 will be chosen with probability 75%.</p>
     * @return Random class
     */
    public Class getRandomClass(Map<Class, Integer> classes) {
        int sum = 0;
        for (int v : classes.values()) {
            sum += v;
        }
        Class[] prob = new Class[sum];
        int index = 0;
        for (Class c : classes.keySet()) {
            for (int i = index; i < classes.get(c) + index; i++) {
                prob[i] = c;
            }
            index += classes.get(c);
        }

        return prob[r.nextInt(sum)];
    }
}
