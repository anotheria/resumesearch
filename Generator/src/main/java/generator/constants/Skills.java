package generator.constants;

import generator.model.Backend;
import generator.model.Frontend;
import generator.model.Skill;

import java.util.HashSet;
import java.util.Set;

/**
 * Skill class represents pairs Skill -> probability.
 */
public final class Skills {
    private Skills() {

    }

    private static Set<Backend> backendSet = new HashSet<>();
    private static Set<Frontend> frontendSet = new HashSet<>();

    static {
        backendSet.add(new Backend("Java", 40));
        backendSet.add(new Backend("Maven", 7));
        backendSet.add(new Backend("Tomcat", 6));
        backendSet.add(new Backend("Elastic Search", 2));
        backendSet.add(new Backend("solr", 2));
        backendSet.add(new Backend("ant", 1));
        backendSet.add(new Backend("PHP", 8));
        backendSet.add(new Backend("HTML/CSS", 3));
        backendSet.add(new Backend("Javascript", 4));
        backendSet.add(new Backend("Jboss", 2));
        backendSet.add(new Backend("Wildfly", 3));
        backendSet.add(new Backend("EJB", 4));
        backendSet.add(new Backend("MYSQL", 4));
        backendSet.add(new Backend("Postgres", 5));
        backendSet.add(new Backend("Mongo", 6));
        backendSet.add(new Backend("HazelCast", 1));
        backendSet.add(new Backend("JUnit", 8));
        backendSet.add(new Backend("Spring", 7));
        backendSet.add(new Backend("ssh", 5));

        frontendSet.add(new Frontend("Javascript", -1));
        frontendSet.add(new Frontend("HTML/CSS", -1));
        frontendSet.add(new Frontend("Angular", 10));
        frontendSet.add(new Frontend("React", 5));
        frontendSet.add(new Frontend("Vue.js", 1));
        frontendSet.add(new Frontend("Node.js", 6));
        frontendSet.add(new Frontend("JWT", 1));
        frontendSet.add(new Frontend("VAADIN", 1));
        frontendSet.add(new Frontend("ExtJS", 3));
    }

    /**
     * @return Set of Backend Skills with their probabilities
     */
    public static Set<Backend> getBackendSet() {
        return backendSet;
    }

    /**
     * @return Set of Frontend Skills with their probabilities
     */
    public static Set<Frontend> getFrontendSet() {
        return frontendSet;
    }

    /**
     * @param clazz that is Backend.class or Frontend.class
     * @return Set of skills belongs to type passed as parameter. Null if no skills for this type present.
     */
    public static Set<? extends Skill> getSet(Class<? extends Skill> clazz) {

        if (clazz.equals(Backend.class)) {
            return getBackendSet();
        }
        if (clazz.equals(Frontend.class)) {
            return getFrontendSet();
        }
        return null;
    }
}
