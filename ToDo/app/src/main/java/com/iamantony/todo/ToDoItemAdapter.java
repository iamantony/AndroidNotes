package com.iamantony.todo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {
    int m_resourse;

    public ToDoItemAdapter(Context context, int resource,
                           List<ToDoItem> items) {
        super(context, resource, items);
        m_resourse = resource;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        ToDoItem item = getItem(position);
        String itemTitle = item.getTitle();
        String itemContent = item.getContent();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String dateStr = sdf.format(item.getCreationDate());

        LinearLayout todoView;
        if (converView == null) {
            todoView = new LinearLayout(getContext());

            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li =
                    (LayoutInflater)getContext().getSystemService(inflater);
            li.inflate(m_resourse, todoView, true);
        }
        else {
            todoView = (LinearLayout)converView;
        }

        TextView dateView = (TextView)todoView.findViewById(R.id.rowDate);
        TextView infoView = (TextView)todoView.findViewById(R.id.rowInfo);

        dateView.setText(dateStr);
        infoView.setText(itemTitle + ": " + itemContent);

        return todoView;
    }
}
