package com.example.androidfirstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Person> listPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPersons = new ArrayList<>();
        listPersons.add(new Person("Juan", 'm'));
        listPersons.add(new Person("Pedro", 'm'));
        listPersons.add(new Person("Luis", 'm'));
        listPersons.add(new Person("Ana", 'f'));
        listPersons.add(new Person("Carla", 'f'));
        listPersons.add(new Person("Maria", 'f'));
        listPersons.add(new Person("Gustavo", 'm'));
        listPersons.add(new Person("Carlos", 'm'));
        listPersons.add(new Person("Marta", 'f'));
        listPersons.add(new Person("Luisa", 'f'));
        listPersons.add(new Person("Fernanda", 'f'));
        listPersons.add(new Person("Jose", 'm'));
        listPersons.add(new Person("Paola", 'f'));
        listPersons.add(new Person("Lucrecia", 'f'));
        listPersons.add(new Person("Oscar", 'm'));

        AdapterPersons adapter = new AdapterPersons(this);
        ListView lv1 = findViewById(R.id.list1);
        lv1.setAdapter(adapter);
    }

    class AdapterPersons extends ArrayAdapter<Person> {

        AppCompatActivity appCompatActivity;

        AdapterPersons(AppCompatActivity context) {
            super(context, R.layout.person, listPersons);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.person, null);

            TextView textView1 = item.findViewById(R.id.textView);
            textView1.setText(listPersons.get(position).getName());

            ImageView imageView1 = item.findViewById(R.id.imageView);
            if (listPersons.get(position).getGender() == 'm')
                imageView1.setImageResource(R.mipmap.hombre);
            else
                imageView1.setImageResource(R.mipmap.mujer);
            return item;
        }
    }
}