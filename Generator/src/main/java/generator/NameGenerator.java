package generator;

import constants.Names;

import java.util.Random;

/**
 * Class for generation Name or Surname.
 */
public final class NameGenerator {

    private static NameGenerator nameGenerator;
    private Random r;

    private NameGenerator() {
        r = new Random();
    }

    /**
     * @return instance of NameGenerator class
     */
    public static NameGenerator getInstance() {
        if (nameGenerator == null) {
            nameGenerator = new NameGenerator();
        }
        return nameGenerator;
    }

    /**
     * @return Name randomly chosen from Names.NAMES array.
     */
    public String getRandomName() {
        return Names.getNAMES()[r.nextInt(Names.getNAMES().length)];
    }

    /**
     * @return letter with dot as the surname. Letter randomly chosen from Names.SURNAME char array.
     */
    public String getRandomSurname() {
        return Names.getSURNAME()[r.nextInt(Names.getSURNAME().length)] + ".";
    }
}
