package net.anotheria.resumesearch.generator;



import net.anotheria.resumesearch.solr.Connector;
import net.anotheria.resumesearch.solr.Manipulator;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.util.*;

/**
 *
 */
public class Generator {
    /**
     * @param args <p>if empty app will generate 50 profiles</p>
     *             <p>If args present it must be in format -command (generate or delete) -number (optional, if not set will generate 50 profiles.
     *             only for -generate command.)</p>
     */
    public static void main(String... args) {
        System.out.println(Arrays.toString(args));
        /**
         * default case, no params
         */
        if (args.length == 0) {
            generate(50);
        }
        /**
         * -generate -n
         * <p>Where -n is the number of profiles to generate</p>
         */
        if (args.length == 2 && args[0].equalsIgnoreCase("generate")) {
            generate(Integer.parseInt(args[1]));
        }
        /**
         * -delete (no params)
         */
        if (args.length == 1 && args[0].equalsIgnoreCase("delete")) {
            Manipulator.deleteAllProfiles(Connector.connect("localhost", 8983, "index"));
        }
        /**
         * -delete -host -port -coreName
         */
        if (args.length == 4 && args[0].equalsIgnoreCase("delete")) {
            Manipulator.deleteAllProfiles(Connector.connect(args[0], Integer.parseInt(args[1]), args[2]));
        }
    }

    /**
     * Generate profiles and index it on solr server.
     * @param profilesNumber that is the number of profiles to generate
     */
    public static void generate(int profilesNumber) {
        TypeProbabilityGenerator t = TypeProbabilityGenerator.getInstance();
        Map<Class, Integer> m = new HashMap<>();
        m.put(Backend.class, 75);
        m.put(Frontend.class, 25);

        List<Profile> profiles = new LinkedList<>();
        for (int i = 0; i < profilesNumber; i++) {

            Class c = t.getRandomClass(m);

            SkillSetGenerator<Skill> sg = SkillSetGenerator.getSkillSetGenerator();
            NameGenerator n = NameGenerator.getInstance();

            Profile p = new Profile(n.getRandomName(), n.getRandomSurname(), sg.generateSkillSet(c));
            profiles.add(p);
        }
        HttpSolrClient solr = Connector.connect("localhost", 8983, "index");
        for(Profile p : profiles){
            Manipulator.addProfile(solr, p);
        }
    }
}
