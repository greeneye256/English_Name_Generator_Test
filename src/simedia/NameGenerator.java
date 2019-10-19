package simedia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameGenerator {

    private final static InputStream boys = NameGenerator.class.getResourceAsStream("/simedia/BoyNames");
    private final static InputStream girls = NameGenerator.class.getResourceAsStream("/simedia/GirlNames");
    private final static InputStream last = NameGenerator.class.getResourceAsStream("/simedia/LastNames");
    private static List<String> boyNames;
    private static List<String> girlNames;
    private static List<String> lastNames;
    private static List<String> firstNames = new ArrayList<>();


    static {
        try {

            boyNames = getArrayOfNamesFromCsv(boys);
            girlNames = getArrayOfNamesFromCsv(girls);
            lastNames = getArrayOfNamesFromCsv(last);
            firstNames.addAll(boyNames);
            firstNames.addAll(girlNames);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getArrayOfNamesFromCsv(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> names = new ArrayList<>();
        String st;
        while ((st = bufferedReader.readLine()) != null) {

            names.add(st.replace(',', ' ').strip());
        }

        return names;
    }

    public static String getRandomBoyFirstName() {

        return boyNames.get(new Random().nextInt(boyNames.size()));
    }

    public static String getRandomGirlFirstName() {
        return girlNames.get(new Random().nextInt(girlNames.size()));
    }

    public static String getRandomLastName() {
        return lastNames.get(new Random().nextInt(lastNames.size()));
    }

    public static String getRandomFirstName() {
        return firstNames.get(new Random().nextInt(firstNames.size()));
    }

    public static String getGenderFromFirstName(String firstName) {
        if (boyNames.contains(firstName)) {
            return "m";
        }
        if (girlNames.contains(firstName)){
            return "f";
        }
        return "no gender";
    }

    public static String getRandomFullName(){
        return getRandomFirstName() + " " + getRandomLastName();
    }
}
