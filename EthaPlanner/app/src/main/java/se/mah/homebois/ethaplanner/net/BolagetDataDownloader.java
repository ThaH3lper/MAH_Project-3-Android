package se.mah.homebois.ethaplanner.net;

import android.os.AsyncTask;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import se.mah.homebois.ethaplanner.db.BolagetDB;
import se.mah.homebois.ethaplanner.models.BolagetArticle;

/**
 * Created by Simon on 10/23/2016.
 */

public class BolagetDataDownloader extends AsyncTask<String, String, List<BolagetArticle>> {

    private final IBolagetDownloadListener listener;
    private final BolagetDB bolagetDB;

    public interface IBolagetDownloadListener {
        void onComplete(List<BolagetArticle> articles);
    }

    public BolagetDataDownloader(BolagetDB bolagetDB, IBolagetDownloadListener listener) {
        this.listener = listener;
        this.bolagetDB = bolagetDB;
    }

    /**
     * Downloading file in background thread
     */
    protected  List<BolagetArticle>  doInBackground(String... params) {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        List<BolagetArticle> articles = null;

        try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();

            articles = parseXML(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close reader
        }
        return articles;
    }

    private List<BolagetArticle> parseXML(URLConnection connection) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

        List<BolagetArticle> articles = new ArrayList<>(100);
        BolagetArticle article = null;
        String lastTag = null;

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tag = parser.getName();

            if (eventType == XmlPullParser.START_DOCUMENT) {

            } else if (eventType == XmlPullParser.START_TAG) {
                if ("artikel".equals(tag)) {
                    article = new BolagetArticle();
                } else {
                    if (article != null) {
                        lastTag = tag;
                    }
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if ("artikel".equals(tag)) {
                    articles.add(article);
                    article = null;
                }
                lastTag = null;
            } else if (eventType == XmlPullParser.TEXT) {
                if (lastTag != null && article != null) {
                    try {
                        article.getClass().getField(lastTag).set(article, parser.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            eventType = parser.next();
        }

        bolagetDB.clearAndUpdate(articles);
        return articles;
    }

    protected void onProgressUpdate(String... progress) {

    }

    @Override
    protected void onPostExecute(List<BolagetArticle>  articles) {
        listener.onComplete(articles);
    }

}
