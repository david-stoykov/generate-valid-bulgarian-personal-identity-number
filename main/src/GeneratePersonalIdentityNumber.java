import java.util.ArrayList;
import java.util.Random;

public class GeneratePersonalIdentityNumber {

    private long[] egnWeights = {2, 4, 8, 5, 10, 9, 7, 3, 6};
    private ArrayList<String> generatedEgn = new ArrayList<>();

    public String generateEgn(String gender) {

        Random random = new Random();

        // I would strongly suggest using some library like Faker to generate those numbers as it's much cleaner.
        String year = String.format("%02d", random.nextInt(99 - 1 + 1) + 1);
        String month = String.format("%02d", random.nextInt(12 - 1 + 1) + 1);
        String day = String.format("%02d", random.nextInt(28 - 1 + 1) + 1);
        String regionDigits = String.format("%02d", random.nextInt(99 - 1 + 1) + 1);
        // Male identity numbers have an even number at the 9th position.
        String evenNumbersFrom0to8 = String.format("%01d", random.nextInt(4) * 2);
        // Female identity numbers have an odd number at the 9th position.
        String oddNumbersFrom1to9 = String.format("%01d", random.nextInt(4) * 2 + 1);

        generatedEgn.add(year);
        generatedEgn.add(month);
        generatedEgn.add(day);
        generatedEgn.add(regionDigits);

        if (gender.equalsIgnoreCase("MALE")) {
            generatedEgn.add(evenNumbersFrom0to8);
        } else {
            generatedEgn.add(oddNumbersFrom1to9);
        }

        long sum = 0;

        String egn = String.join("", generatedEgn);

        for (int i = 0; i < egnWeights.length; i++) {
            sum = sum + (Character.getNumericValue(egn.charAt(i)) * egnWeights[i]);
        }

        long remainder = sum % 11;
        if (remainder == 10) {
            remainder = 0;
        }
        return egn.concat(String.valueOf(remainder));
    }
}