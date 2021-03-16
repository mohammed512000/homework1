package com.example.applecationfirbase1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private DatabaseReference mDatabase;
    private Button butAdd;
    private EditText edText;
    private EditText editText;
    private ListView listView;
    private ArrayList<String> arrayList =new  ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);

         butAdd = (Button) findViewById(R.id.butadd);
         edText = (EditText) findViewById(R.id.et_database);
        editText = (EditText) findViewById(R.id.et_address);
         listView = (ListView) findViewById(R.id.batabase_list_view);

         listView.setAdapter(adapter);

         butAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mDatabase.push().setValue(edText.getText().toString(),editText.getText().toString());
             }
         });

         mDatabase.addChildEventListener(new ChildEventListener() {
             @Override
             public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                 String string = snapshot.getValue(String.class);
                 arrayList.add(string);
                 adapter.notifyDataSetChanged();

             }

             @Override
             public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

             }

             @Override
             public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                 String string = snapshot.getValue(String.class);
                 arrayList.add(string);
                 adapter.notifyDataSetChanged();
             }

             @Override
             public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

    }
}