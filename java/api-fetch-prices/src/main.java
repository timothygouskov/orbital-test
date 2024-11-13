import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Prices {
    public static void main(String[] args) {
        Optional.ofNullable(System.getenv("AV_API_KEY")).orElseThrow(() -&gt; new IllegalStateException("Woops! AV_API_KEY env var is null!"));
        String apiKey = System.getenv("AV_API_KEY");
        String symbol = "ICE";

        try {
            String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=1min&apikey=" + apiKey;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONObject timeSeries = jsonResponse.getJSONObject("Time Series (1min)");

            String latestTimestamp = timeSeries.keys().next();
            JSONObject latestData = timeSeries.getJSONObject(latestTimestamp);
            String price = latestData.getString("4. close");

            System.out.println("Here is the current price for " + symbol + " is: $" + price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
