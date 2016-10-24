package se.mah.homebois.ethaplanner.net;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.models.BolagetArticle;
import se.mah.homebois.ethaplanner.views.ListContent.ListViewItems;

/**
 * Created by Simon on 10/23/2016.
 */

public class BolagetImageDownloader implements DrinkTypeImageUrlMapper.IImageUrlListener{

    private final ImageView imageView;
    private final ListViewItems article;

    public BolagetImageDownloader(ImageView imageView, ListViewItems article ) {
        this.imageView = imageView;
        this.article = article;

        if (article.getImageUrl() == null) {
            new DrinkTypeImageUrlMapper(this).execute(article);
        } else {
            onImageUrlReady(article.getImageUrl());
        }
    }

    @Override
    public void onImageUrlReady(String url) {
        if (url == null || url.equals("https:")) {
            return;
        }

        article.setImageUrl(url);
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
