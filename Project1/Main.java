package Project1;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        try {
            currencyC currency = currencyC.getCurrency();
            //Use this next line of code to convert USD to other currencies
            System.out.println(currency.convertUSD(200, "ZAR"));
            //Use this next line of code to convert any currency to any other currency
            System.out.println(currency.convert(2000, "CAD", "ZAR"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(System.err);
        }
    }
}