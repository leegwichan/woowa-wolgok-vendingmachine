package vendingmachine.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import vendingmachine.domain.Merchandise;
import vendingmachine.domain.Money;

public class MerchandiseParser {
    public static List<Merchandise> parseMerchandises(String primitive) {
        String[] merchandises = primitive.split(";");
        validateMerchandises(merchandises);
        return Arrays.stream(merchandises)
                .map(MerchandiseParser::parseMerchandise)
                .collect(Collectors.toList());
    }

    private static Merchandise parseMerchandise(String primitive) {
        primitive = unFormat(primitive);
        String[] attributes = primitive.split(",");
        validateAttributes(attributes);
        try {
            return new Merchandise(attributes[0], new Money(attributes[1]), Integer.parseInt(attributes[2]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateMerchandises(String[] merchandises) {
        if(merchandises.length < 1) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateAttributes(String[] attributes) {
        if (attributes.length != 3) {
            throw new IllegalArgumentException();
        }
    }

    public static String unFormat(String primitive) {
        Pattern pattern = Pattern.compile("^\\[(.*?)]$");
        Matcher matcher = pattern.matcher(primitive);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        throw new IllegalArgumentException();
    }
}
