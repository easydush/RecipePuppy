import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Using {
    protected class Recipe{
        private String title;
        private String href;
        private String ingredients;
        private URI thumbnail;
        public void print() throws IOException, URISyntaxException {
            System.out.println("Recipe:"+title);
            System.out.println("You need to have: "+ingredients);
            System.out.println(href);
        }
    }
    public static void main(String[] args) throws URISyntaxException, IOException {
        Scanner scan = new Scanner(System.in);
        String url = "http://www.recipepuppy.com/api/?";
        System.out.println("Enter the comma delimited ingredients");
        url+="i=";
        url+=scan.nextLine();
        System.out.println("Enter the query");
        url+="&q=";
        url+=scan.nextLine();
        URI uri = new URI(url);
        URL obj = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String query = response.toString();
        query=query.substring(query.indexOf("results")+10,query.indexOf("thumbnail")-2)+"}";
        Gson gson = new Gson();
        Recipe rec = gson.fromJson(query, Recipe.class);
        rec.print();
        SiteShower show = new SiteShower();
        show.getSite(rec.href);
    }
}
