package se.mah.homebois.ethaplanner.net;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import se.mah.homebois.ethaplanner.models.BolagetArticle;

/**
 * Created by Simon on 10/23/2016.
 */

public class BolagetImageDownloader implements DrinkTypeImageUrlMapper.IImageUrlListener{

    private final ImageView imageView;

    public BolagetImageDownloader(ImageView imageView, BolagetArticle article ) {
        this.imageView = imageView;
        new DrinkTypeImageUrlMapper(this).execute(article);
    }

    @Override
    public void onImageUrlReady(String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
