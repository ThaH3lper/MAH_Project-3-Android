package se.mah.homebois.ethaplanner.net;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import se.mah.homebois.ethaplanner.Globals;
import se.mah.homebois.ethaplanner.views.ListContent.ListViewItems;

/**
 * Created by Simon on 10/23/2016.
 */

public class DrinkTypeImageUrlMapper extends AsyncTask<ListViewItems, Void, String> {

    private final IImageUrlListener listener;

    public interface IImageUrlListener {
        void onImageUrlReady(String url);
    }

    public DrinkTypeImageUrlMapper(IImageUrlListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(ListViewItems... params) {
        ListViewItems article = params[0];

        String url = Globals.BOLAGET_IMAGE_URL;

        String type = "sprit";
        if (article.getType().equals("Rött vin")) {
            type = "roda-viner";
        } else if (article.getType().equals("Vitt vin")) {
            type = "vita-viner";
        } else if (article.getType().equals("Rosévin")) {
            type = "roseviner";
        } else if (article.getType().contains("vin")) {
            type = "vin";
        } else if (article.getType().contains("Öl")) {
            type = "ol";
        } else if (article.getType().contains("Alkoholfritt")) {
            type = "alkoholfritt";
        }

        String name = article.getTitle()
                .replace("å", "a")
                .replace("ä", "a")
                .replace("ö", "o")
                .replace("Å", "A")
                .replace("Ä", "A")
                .replace("Ö", "O")
                .replace("é", "e")
                .replace("í", "i")
                .replace("&", "")
                .replace(":", "")
                .replace("â", "a")
                .replace("ë", "e")
                .replace("ó", "o")

                .replace(",", "")
                .replace("'", "")
                .replace("  ", " ")
                .replace(" ", "-")
                + "-" + article.getId();

        url = String.format(url, type, name).trim();

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();

            Elements img = doc.select(".carousel-container img");
            return "https:" + img.attr("src");
        } catch (IOException e) {
            // e.printStackTrace();
            Log.d("URLMapper", e.getMessage() + url);
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onImageUrlReady(s);
    }
}
