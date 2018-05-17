package solr;

import constants.Skills;
import model.Profile;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;

public class Test {
    public static void main(String... args) throws IOException, SolrServerException {
        HttpSolrClient solr = Connector.connect("localhost", 8983, "index");
//        solr.addBean( new Profile("name", "surname", Skills.getFrontendSet()) );
//        solr.commit();


        Profile p = new Profile("name", "surname", Skills.getFrontendSet());
        Manipulator.deleteAllProfiles(solr);
        //Manipulator.addProfile(solr, p);
    }
}
