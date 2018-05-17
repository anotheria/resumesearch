package generator.constants;

/**
 * This class represents list of possible names and surnames.
 */
public final class Names {
    private Names() {

    }

    private static final String[] NAMES = new String[]{
            "Igor", "Roman", "Eugene", "Sergei", "Nikolai", "Artem", "Andriy", "Anton", "Sasha", "Alex", "Alexei", "Miroslav", "Konstantin", "Leonid",
            "Ilja", "Georgiy", "Denis", "Tatiana", "Michail", "Anna"
    };

    private static final char[] SURNAMES = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

    /**
     * @return Array of possible Names
     */
    public static String[] getNAMES() {
        return NAMES;
    }

    /**
     * @return Array of possible Surnames
     */
    public static char[] getSURNAME() {
        return SURNAMES;
    }
}
