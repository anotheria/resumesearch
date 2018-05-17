package solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

public final class Connector {
    private static HttpSolrClient solr;

    private Connector() {

    }

    public static HttpSolrClient connect(String address, int port, String name) {
        if (solr == null) {
            String urlString = "http://" + address + ":" + port + "/solr/" + name;
            solr = new HttpSolrClient.Builder(urlString).build();
            solr.setParser(new XMLResponseParser());
        }
        return solr;
    }
}
