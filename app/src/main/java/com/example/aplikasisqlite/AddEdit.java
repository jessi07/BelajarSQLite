package com.example.aplikasisqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasisqlite.helper.DbHelper;

public class AddEdit extends AppCompatActivity {
    EditText id_teks, name_teks, address_teks;
    Button btnsubmit, btncancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id_teks = (EditText) findViewById(R.id.id_number);
        name_teks = (EditText) findViewById(R.id.input_name);
        address_teks = (EditText) findViewById(R.id.input_address);
        btnsubmit = (Button) findViewById(R.id.submit);
        btncancel = (Button) findViewById(R.id.cancel);

        id = getIntent().getStringExtra(MainActivity.Tag_id_number);
        name = getIntent().getStringExtra(MainActivity.Tag_name_input);
        address = getIntent().getStringExtra(MainActivity.Tag_address_input);

        if (id == null || id == ""){
            setTitle("Add Data");
        } else{
            setTitle("Edit Data");
            id_teks.setText(id);
            name_teks.setText(name);
            address_teks.setText(address);
        }
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (id_teks.getText().toString().equals("")){
                        save();
                    } else{
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Kosongkan semua Edit Text
    private void blank(){
        name_teks.requestFocus();
        id_teks.setText(null);
        name_teks.setText(null);
        address_teks.setText(null);
    }

    //Save
    private void save(){
        if (String.valueOf(name_teks.getText()).equals(null) || String.valueOf(address_teks.getText()).equals("") ||
                String.valueOf(address_teks.getText()).equals(null) || String.valueOf(address_teks.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address...", Toast.LENGTH_SHORT).show();
        } else{
            SQLite.insert(name_teks.getText().toString().trim(), address_teks.getText().toString().trim());
            blank();
            finish();
        }
    }

    //Update data kedalam Database SQLite
    private void edit(){
        if(String.valueOf(name_teks.getText()).equals(null) || String.valueOf(name_teks.getText()).equals("") ||
                String.valueOf(address_teks.getText()).equals(null) || String.valueOf(address_teks.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else{
            SQLite.update(Integer.parseInt(id_teks.getText().toString().trim()), name_teks.getText().toString().trim(),
                    address_teks.getText().toString().trim());
            blank();
            finish();
        }
    }
}
