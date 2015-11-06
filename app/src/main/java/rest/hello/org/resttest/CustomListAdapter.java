package rest.hello.org.resttest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {
    String[] textIM, textTitle;
    Integer[] image_id;
    Context context;

    public CustomListAdapter(Activity context, Integer[] image_id, String[] textIM, String[] textTitle) {
        super(context, R.layout.list_row, textIM);
        // TODO Auto-generated constructor stub
        this.textIM = textIM;
        this.textTitle = textTitle;
        this.image_id = image_id;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.list_row, null,
                true);
        TextView textViewIM = (TextView) single_row.findViewById(R.id.textViewIM);
        TextView textViewTitle = (TextView) single_row.findViewById(R.id.textViewTitle);
        ImageView imageView = (ImageView) single_row.findViewById(R.id.imageView);
        textViewIM.setText(textIM[position]);
        textViewTitle.setText(textTitle[position]);
        imageView.setImageResource(image_id[position]);
        return single_row;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }
}