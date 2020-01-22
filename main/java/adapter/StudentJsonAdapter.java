package adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.attendancemanager.R;

import java.util.List;

public class StudentJsonAdapter extends ArrayAdapter<String> {
    Context context;

    List<String> name;
    List<String> rollno;

    public StudentJsonAdapter(@NonNull Context context,
                              int resource, int textViewResourceId,
                              @NonNull List<String> objects,
                              List<String> rollno) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.name = objects;
        this.rollno = rollno;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.slideviews,parent,false);

        TextView sname = view.findViewById(R.id.name);
        TextView srollno = view.findViewById(R.id.rollno);
        sname.setText(name.get(position));
        srollno.setText(rollno.get(position));
        return view;
    }



}
