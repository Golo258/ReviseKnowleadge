package org.example.Revise.MultiThreading.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtoDom {

    public void  getFlats () throws Exception{
        try {
            String url = "https://www.otodom.pl/pl/wyniki/sprzedaz/mieszkanie/malopolskie/krakow";
            URL otoURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) otoURL.openConnection();
            // Ustaw nagłówek użytkownika, aby wydawało się, że to przeglądarka
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder pageContent = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    pageContent.append(inputLine);
                    pageContent.append(System.lineSeparator());
                }
                in.close();
                String content = pageContent.toString();
                int indexOfContentOffer= content.indexOf("https://www.otodom.pl/pl/oferta/");
//                String cuttedString = content.substring(indexOfContentOffer)
//                        .split("\",itemoffered"");
    //------------------
                Pattern pattern = Pattern.compile("\"([https://www.otodom.pl/pl/oferta/]+)\"");
                Matcher matcher = pattern.matcher(pageContent);

                while (matcher.find()) {
                    String offerLink = matcher.group(1);
                    System.out.println("Link do oferty: " + offerLink);
                }
            } else {
                System.out.println("Błąd podczas pobierania strony. Kod odpowiedzi HTTP: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

}