package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.net.URIBuilder;
import org.json.JSONObject;

public class ExchangeRatesHelper {
	
	public static HashMap<String, Double> values = new HashMap<>();
	public static void getExchangeRates() {
		System.out.println(55);
		
		List<String> exchanges = new LinkedList<>();
		exchanges.add("EUR");
		exchanges.add("USD");
		exchanges.add("TRY");
		exchanges.add("CHF");
		exchanges.add("GBP");
		exchanges.add("JPY");
		exchanges.add("HUF");
		
		for(String currency : exchanges) {
			Double value = exchangeAmount(currency, "RON");
			values.put(currency, value);
		}
		
		
	}
	public static double exchangeAmount(String from,String to) {
		try {

			URI uri = new URIBuilder("https://api.apilayer.com/exchangerates_data/convert") //
					.addParameter("apikey", "Ccqnivx4VZDQNj7pBen2SxmE8WTc38dQ") //
					.addParameter("amount", "1") //
					.addParameter("from",from) //
					.addParameter("to",to) // 
					.build();
			HttpGet request = new HttpGet(uri);

			CloseableHttpClient client = HttpClients.createDefault();

			CloseableHttpResponse response = client.execute(request);
			if (response.getCode() == 200) {
				InputStream inputStream = response.getEntity().getContent();
				String responseAsString = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
						.lines().collect(Collectors.joining("\n"));
				client.close();
				System.out.println(responseAsString);
				JSONObject jsonObj = new JSONObject(responseAsString);
				double value = jsonObj.getDouble("result");
				System.out.println(value);
				return  value;
			}
			
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
		
	}
}
