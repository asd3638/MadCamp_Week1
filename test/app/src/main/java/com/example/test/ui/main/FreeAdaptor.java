package com.example.test.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.example.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.test.ui.main.FreeFragment;

import javax.security.auth.callback.Callback;

public class FreeAdaptor extends ArrayAdapter<Food> {
    private Context context;
    private final ArrayList<Food> foods;
    private Callback callback;
    public static int new_kcal;
    FreeFragment fragment;
    public FreeAdaptor(Context context, ArrayList<Food> foods) {
        super(context,-1,foods);
        this.context = context;
        this.foods = foods;
        this.callback = null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_food, parent, false);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.food_name);
        nameTextView.setText(foods.get(position).getName());
        TextView kcalTextView = (TextView) rowView.findViewById(R.id.food_kcal);
        kcalTextView.setText(foods.get(position).getkcal());
        TextView numTextView = (TextView) rowView.findViewById(R.id.food_num);
        numTextView.setText("수량: " + foods.get(position).getNum());

        ImageButton plus_botton = rowView.findViewById(R.id.plus_button);
        plus_botton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                foods.get(position).setNum(foods.get(position).getNum()+1);
                numTextView.setText("수량: " + foods.get(position).getNum());
                new_kcal =  Integer.parseInt(foods.get(position).getkcal().substring(0, foods.get(position).getkcal().length()-4));

                JSONObject jsonObject5 = new JSONObject();
                JSONArray newArray = new JSONArray();
                try {
                    for (int i = 0; i < foods.size(); i++) {
                        JSONObject jsonObject1 = new JSONObject();
                        try {
                            jsonObject1.put("name", foods.get(i).getName());
                            jsonObject1.put("kcal", foods.get(i).getkcal());
                            jsonObject1.put("num", foods.get(i).getNum());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        newArray.put(jsonObject1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    jsonObject5.put("Foods", newArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String filename = "Foods.json";
                try {
                    try (FileOutputStream fos = getContext().openFileOutput(filename, Context.MODE_PRIVATE)) {
                        fos.write(jsonObject5.toString().getBytes());
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        ImageButton minus_botton = rowView.findViewById(R.id.minus_button);
        minus_botton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                foods.get(position).setNum(foods.get(position).getNum()-1);
                numTextView.setText("수량: " + foods.get(position).getNum());

                JSONObject jsonObject5 = new JSONObject();
                JSONArray newArray = new JSONArray();
                try {
                    for (int i = 0; i < foods.size(); i++) {
                        JSONObject jsonObject1 = new JSONObject();
                        try {
                            jsonObject1.put("name", foods.get(i).getName());
                            jsonObject1.put("kcal", foods.get(i).getkcal());
                            jsonObject1.put("num", foods.get(i).getNum());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        newArray.put(jsonObject1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    jsonObject5.put("Foods", newArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String filename = "Foods.json";
                try {
                    try (FileOutputStream fos = getContext().openFileOutput(filename, Context.MODE_PRIVATE)) {
                        fos.write(jsonObject5.toString().getBytes());
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // View에 Data 세팅
        return rowView;
    }

    /*public class ViewHolder {
        private TextView txt_name;
        public ViewHolder(View convertView) {
            txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        }
    }*/
}