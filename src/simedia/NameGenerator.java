package simedia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class NameGenerator {

    private final static File fileBoyNames = new File("src/simedia/BoyNames");
    private final static File fileGirlNames = new File("src/simedia/GirlNames");
    private final static File fileLastNames = new File("src/simedia/LastNames");
    private static List<String> boyNames;
    private static List<String> girlNames;
    private static List<String> lastNames;
    private static List<String> firstNames = new ArrayList<>();


    static {
        try {

            boyNames = getArrayOfNamesFromCsv(fileBoyNames);
            girlNames = getArrayOfNamesFromCsv(fileGirlNames);
            lastNames = getArrayOfNamesFromCsv(fileLastNames);
            firstNames.addAll(boyNames);
            firstNames.addAll(girlNames);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getArrayOfNamesFromCsv(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> names = new ArrayList<>();
        String st;
        while ((st = bufferedReader.readLine()) != null) {

            names.add(st.replace(',', ' ').strip());
        }

        return names;
    }

    static String getRandomBoyFirstName() {

        return boyNames.get(new Random().nextInt(boyNames.size()));
    }

    static String getRandomGirlFirstName() {
        return girlNames.get(new Random().nextInt(girlNames.size()));
    }

    static String getRandomLastName() {
        return lastNames.get(new Random().nextInt(lastNames.size()));
    }

    static String getRandomFirstName() {
        return firstNames.get(new Random().nextInt(firstNames.size()));
    }

    static String getGenderFromFirstName(String firstName) {
        if (boyNames.contains(firstName)) {
            return "m";
        }
        if (girlNames.contains(firstName)){
            return "f";
        }
        return "no gender";
    }

    static String getRandomFullName(){
        return getRandomFirstName() + " " + getRandomLastName();
    }
}
