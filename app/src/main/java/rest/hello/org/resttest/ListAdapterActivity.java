package rest.hello.org.resttest;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapterActivity extends ArrayAdapter<String> {
    String[] textDate, textType, textOperator, textDescription;
    Context context;

    public ListAdapterActivity(Context context, String[] textDate, String[] textType, String[] textOperator, String[] textDescription) {
        super(context, R.layout.list_row_activity);
        this.textDate = textDate;
        this.textType = textType;
        this.textOperator = textOperator;
        this.textDescription = textDescription;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.from(context).inflate(R.layout.list_row_activity, parent,
                false);
        TextView textViewDate = (TextView) single_row.findViewById(R.id.textDate);
        TextView textViewType = (TextView) single_row.findViewById(R.id.textType);
        TextView textViewOperator = (TextView) single_row.findViewById(R.id.textOperator);
        TextView textViewDescription = (TextView) single_row.findViewById(R.id.textDescription);

        textViewDate.setText(textDate[position]);
        textViewType.setText(textType[position]);
        textViewOperator.setText(textOperator[position]);
        textViewDescription.setText(textDescription[position]);


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