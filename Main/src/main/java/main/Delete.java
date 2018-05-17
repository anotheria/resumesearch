package main;

import solr.Connector;
import solr.Manipulator;

public class Delete {
    public static void main(String[] args){
        if(args.length == 3){
            Manipulator.deleteAllProfiles(Connector.connect(args[0], Integer.parseInt(args[1]), args[2]));
        }
        Manipulator.deleteAllProfiles(Connector.connect("localhost", 8983, "index"));
    }
}
