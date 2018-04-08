package com.andy.edittextutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.Toast;

import com.andy.length_filter.LengthFilter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etInput = findViewById(R.id.etInput);

        LengthFilter lengthFilter = new LengthFilter(30);
        etInput.setFilters(new InputFilter[]{lengthFilter});

        lengthFilter.setLengthWarnListener(new LengthFilter.LengthWarnListener() {
            @Override
            public void onLengthWarn() {
                Toast.makeText(etInput.getContext(), "只能输入30个字符/15个汉字", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
