package rest.hello.org.resttest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IncidentListAdapter extends ArrayAdapter<String> {
    String[] textIM, textTitle, textStatus, textCrit, textDate;
    //Integer[] image_id;
    Context context;

    public IncidentListAdapter(Activity context, String[] textIM, String[] textStatus, String[] textTitle, String[] textCrit, String[] textDate) {
        super(context, R.layout.list_row, textIM);
        this.textIM = textIM;
        this.textTitle = textTitle;
        this.textStatus = textStatus;
        this.textCrit = textCrit;
        this.textDate = textDate;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.list_row, null,
                true);
        TextView textViewIM = (TextView) single_row.findViewById(R.id.textViewIM);
        TextView textViewTitle = (TextView) single_row.findViewById(R.id.textViewTitle);
        TextView textViewStatus = (TextView) single_row.findViewById(R.id.textViewStatus);
        TextView textViewCrit = (TextView) single_row.findViewById(R.id.textViewCrit);
        TextView textViewDate = (TextView) single_row.findViewById(R.id.textViewDate);

        textViewIM.setText(textIM[position]);
        textViewTitle.setText(textTitle[position]);
        textViewStatus.setText(textStatus[position]);
        textViewCrit.setText(textCrit[position]);
        textViewDate.setText(textDate[position]);

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