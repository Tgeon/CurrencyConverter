package Project1;

import Project1.etc.unregCurrencyException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.net.URISyntaxException;
import java.net.URI;

public class currencyC {
    private double NZD;
    private double CAD;
    private double JPY;
    private double EUR;
    private double GBP;
    private double ZAR;

    private currencyC() throws IOException, URISyntaxException {
        final String END = apiKey.END;
        URL url = new URI(END).toURL();
        HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
        connect.setRequestMethod("GET");

        int responseCode = connect.getResponseCode();
        System.out.println(responseCode);
        try (InputStreamReader isr = new InputStreamReader(connect.getInputStream());
             BufferedReader br = new BufferedReader(isr)) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JsonObject object = JsonParser.parseString(sb.toString()).getAsJsonObject();
            JsonObject currency = object.get("data").getAsJsonObject();

            NZD = currency.get("NZD").getAsDouble();
            CAD = currency.get("CAD").getAsDouble();
            JPY = currency.get("JPY").getAsDouble();
            EUR = currency.get("EUR").getAsDouble();
            GBP = currency.get("GBP").getAsDouble();
            ZAR = currency.get("ZAR").getAsDouble();
        }
    }

    public static currencyC getCurrency() throws IOException, URISyntaxException {
        return new currencyC();
    }

    public double convertUSD(double amount, String toCode) {
        return getCode(toCode) * amount;
    }

    public double convert(double amount, String fromCode, String toCode) {
        double uSD = amount/getCode(fromCode);
        return convertUSD(uSD,toCode);
    }

    private double getCode(String code) {
        return switch (code.toUpperCase()) {
            case "EUR" -> EUR;
            case "GBP" -> GBP;
            case "ZAR" -> ZAR;
            case "CAD" -> CAD;
            case "NZD" -> NZD;
            case "JPY" -> JPY;
            default -> throw new unregCurrencyException("Please Enter a Valid Currency Code");
        };
    }
}