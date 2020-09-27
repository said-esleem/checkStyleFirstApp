package com.example.mapappwork01;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;
import java.util.List;

public final class  InfoAdapter implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private List<Restaurants> list;
    private String description;
    private String photo;
    public InfoAdapter(final Context context,
                       final List<Restaurants> list,
                       final String description,
                       final String photo) {
        this.context = context;
        this.list = list;
        this.description = description;
        this.photo = photo;
    }
    public InfoAdapter(final Context context) {
        this.context = context;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }
    @Override
    public View getInfoContents(Marker marker) {
        View view = LayoutInflater.from(context).inflate(R.layout.marker_item,null);
        TextView textView1 = view.findViewById(R.id.textView1);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView2 = view.findViewById(R.id.textView2);
        textView1.setText(marker.getTitle());
        textView2.setText(description);
        Picasso.get().load(photo).into(imageView);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(500, 500));
        return view;
    }
}
