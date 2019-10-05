package net.schnorbus.filedatefixer;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        final Button btn1 = findViewById(R.id.btn1);
        final Button btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setText("pressed");
            }
        });

        final ListView listView = findViewById(R.id.listview);
        final TextAdapter textAdapter = new TextAdapter();
        listView.setAdapter(textAdapter);

        List<String> exampleData = new ArrayList<>();
        for (int i = 0; i<200;i++) {
            exampleData.add(String.valueOf(i));
        }

        textAdapter.setData(exampleData);
    }

    class TextAdapter extends BaseAdapter {

        private List<String> data = new ArrayList<>();

        public void setData(List<String> data) {
            if (data!=null) {
                this.data.clear();
                if (data.size()>0) {
                    this.data.addAll(data);
                }
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return this.data.size();
        }

        @Override
        public String getItem(int position) {
            return this.data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                convertView.setTag(new ViewHolder((TextView) convertView.findViewById(R.id.textItem)));
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            final String item = getItem(position);
            holder.info.setText(item);
            return convertView;
        }

        class ViewHolder {
            TextView info;

            ViewHolder(TextView info) {
                this.info = info;
            }
        }
    }
}
