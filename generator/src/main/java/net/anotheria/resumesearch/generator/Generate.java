package net.anotheria.resumesearch.generator;

/**
 * Class initiates generating of 50 profiles if parameter is not present.
 */
public class Generate {

    public static void main(String[] args){
        int n = 50;
        if(args.length != 0 && Integer.parseInt(args[0]) > 0){
            n = Integer.parseInt(args[0]);
        }
        Generator.generate(n);
    }
}
