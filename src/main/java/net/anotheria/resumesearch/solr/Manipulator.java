package net.anotheria.resumesearch.solr;

import net.anotheria.resumesearch.model.Profile;
import net.anotheria.resumesearch.model.Skill;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class Manipulator {
    private Manipulator() {

    }

    public static void addProfile(HttpSolrClient solr, Profile p) {
        List<String> names = new LinkedList<>();

        for (Skill s : (Set<Skill>) p.getSkillSet()) {
            names.add(s.getName());
        }


        SolrInputDocument document = new SolrInputDocument();
        document.addField("firstName", p.getFirstName());
        document.addField("lastName", p.getLastName());
        document.addField("skills", names);
        try {
            solr.add(document);
            solr.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteAllProfiles(HttpSolrClient solr) {
        try {
            solr.deleteByQuery("*:*");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
