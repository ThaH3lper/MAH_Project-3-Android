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

public class ListAdapter extends ArrayAdapter<ListViewItems> {
    private LayoutInflater inflater;


    public ListAdapter(Context context, List<ListViewItems> Items) {
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

        ImageView Image;

        if (convertView == null) {
            convertView = (LinearLayout)inflater.inflate(R.layout.listrow, parent, false);
        }

        Title = (TextView)convertView.findViewById(R.id.twName);
        Alcohol = (TextView)convertView.findViewById(R.id.twAlcohol);
        Price = (TextView)convertView.findViewById(R.id.twPrice);
        APK = (TextView)convertView.findViewById(R.id.twPrice);

        Image = (ImageView)convertView.findViewById(R.id.ivDrink);

        Title.setText(this.getItem(position).getTitle());
        Alcohol.setText(this.getItem(position).getAlcohol());
        Price.setText(this.getItem(position).getPrice());
        APK.setText(this.getItem(position).getAPK());

        //  FIXA BILD ------>  Image.setImageResource(this.getItem(position).getIncomeCategory().getIcon());

        return convertView;

    }
}
