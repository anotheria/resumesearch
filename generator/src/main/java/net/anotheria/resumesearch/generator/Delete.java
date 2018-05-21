package net.anotheria.resumesearch.generator;

import net.anotheria.resumesearch.solr.Connector;
import net.anotheria.resumesearch.solr.Manipulator;

/**
 * Class initiates deleting of all profiles from solr server.
 */
public class Delete {
    public static void main(String[] args){
        if(args.length == 3){
            Manipulator.deleteAllProfiles(Connector.connect(args[0], Integer.parseInt(args[1]), args[2]));
        }
        Manipulator.deleteAllProfiles(Connector.connect("localhost", 8983, "index"));
    }
}
