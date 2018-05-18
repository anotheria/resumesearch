package net.anotheria.resumesearch.generator;

import java.util.Map;
import java.util.Random;

public final class TypeProbabilityGenerator {

    private static TypeProbabilityGenerator INSTANCE;
    private Random r;

    private TypeProbabilityGenerator(){
        r = new Random();
    }

    public static TypeProbabilityGenerator getInstance(){
        if(INSTANCE == null){
            INSTANCE = new TypeProbabilityGenerator();
        }
        return INSTANCE;
    }

    public Class getRandomClass(Map<Class, Integer> classes){
        int sum = 0;
        for(int v : classes.values()){
            sum += v;
        }
        Class[] prob = new Class[sum];
        int index = 0;
        for(Class c : classes.keySet()){
            for(int i=index; i<classes.get(c)+index; i++){
                prob[i] = c;
            }
            index += classes.get(c);
        }

        return  prob[r.nextInt(sum)];
    }
}
