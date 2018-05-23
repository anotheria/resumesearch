package net.anotheria.resumesearch.solr;


import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

/**
 * How to optimise connect method?
 */
public final class Connector {

    private static HttpSolrClient solr;
    private static String address;
    private static int port;
    private static String name;

    private Connector() {

    }

    /**
     * Initiates HttpSolrClient with given params.
     * @param address of server
     * @param port of server
     * @param name of core
     * @return HttpSolrClient instance
     */
    public static HttpSolrClient connect(String address, int port, String name) {
        if(solr == null){
            init(address, port, name);
        }else{
            if(!Connector.name.equals(name) || Connector.port != port || !Connector.address.equals(address)){
                init(address, port, name);
            }
        }

        return solr;
    }

    private static void init(String address, int port, String name){
        String urlString = "http://" + address + ":" + port + "/solr/" + name;
        solr = new HttpSolrClient.Builder(urlString).build();
        solr.setParser(new XMLResponseParser());
        Connector.address = address;
        Connector.port = port;
        Connector.name = name;
    }
}
