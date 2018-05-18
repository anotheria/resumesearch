package net.anotheria.resumesearch.main;

public class Generate {

    public static void main(String[] args){
        int n = 50;
        if(args.length != 0 && Integer.parseInt(args[0]) > 0){
            n = Integer.parseInt(args[0]);
        }
        Main.generate(n);
    }
}
