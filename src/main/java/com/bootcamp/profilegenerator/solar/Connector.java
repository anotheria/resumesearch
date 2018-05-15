package com.bootcamp.profilegenerator.solar;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

public final class Connector {

    private Connector(){

    }

    public static HttpSolrClient connect(String address, int port, String name){
        String urlString = "http://"+address+":"+port+"/solr/"+name;
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
        solr.setParser(new XMLResponseParser());
        return solr;
    }
}
