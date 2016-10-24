package se.mah.homebois.ethaplanner.views.ListContent;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.net.BolagetImageDownloader;

public class DrinkResultListAdapter extends ArrayAdapter<ListViewItems> {
    private LayoutInflater inflater;


    public DrinkResultListAdapter(Context context, List<ListViewItems> Items) {
        super(context, R.layout.listrow, Items);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        TextView Title;
        TextView Alcohol;
        TextView Price;
        TextView APK;
        TextView Group;

        ImageView Image;

        if (convertView == null || true) { // Test if images are more reliable
            convertView = inflater.inflate(R.layout.listrow, parent, false);
        }

        Title = (TextView)convertView.findViewById(R.id.twName);
        Alcohol = (TextView)convertView.findViewById(R.id.twAlcohol);
        Price = (TextView)convertView.findViewById(R.id.twPrice);
        APK = (TextView)convertView.findViewById(R.id.twAPK);
        Group = (TextView)convertView.findViewById(R.id.twGroup);

        ListViewItems item = getItem(position);

        Title.setText(item.getTitle());
        Alcohol.setText(item.getAlcohol());
        Price.setText(item.getPrice());
        APK.setText(item.getAPK());
        Group.setText(item.getType());

        // Load Image
        Image = (ImageView)convertView.findViewById(R.id.ivDrink);
        Image.setImageResource(R.drawable.examplelistitem);
        new BolagetImageDownloader(Image, item);

        return convertView;
    }
}
