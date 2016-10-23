package se.mah.homebois.ethaplanner.net;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import se.mah.homebois.ethaplanner.Globals;
import se.mah.homebois.ethaplanner.models.BolagetArticle;

/**
 * Created by Simon on 10/23/2016.
 */

public class DrinkTypeImageUrlMapper extends AsyncTask<BolagetArticle, Void, String> {

    private final IImageUrlListener listener;

    public interface IImageUrlListener {
        void onImageUrlReady(String url);
    }
    
    public  DrinkTypeImageUrlMapper(IImageUrlListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(BolagetArticle... params) {
        BolagetArticle article = params[0];

        String url = Globals.BOLAGET_IMAGE_URL;

        String type = "sprit";

        String name = article.Namn
                .replace(" ", "-")
                .replace("å", "a")
                .replace("ä", "a")
                .replace("ö", "o")
                .replace("Å", "A")
                .replace("Ä", "A")
                .replace("Ö", "O")
                + "-" + article.nr;

        url = String.format(url, type, name).trim();

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();

            Elements img = doc.select(".carousel-container img");
            return img.attr("src");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onImageUrlReady(s);
    }
}
