package com.example.ecommercemobileapp2hand.Views.Login;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ecommercemobileapp2hand.R;
import com.example.ecommercemobileapp2hand.Views.Login.Onboarding.Age;
import com.example.ecommercemobileapp2hand.Views.Login.Onboarding.AgeAdapter;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    private Spinner spiAge;
    private AgeAdapter ageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spiAge= findViewById(R.id.spi_age);
        ageAdapter = new AgeAdapter(this,R.layout.age_selected,getList());
        spiAge.setAdapter(ageAdapter);
//        spiAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,ageAdapter.getItem(position).getAge(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }


    private List<Age> getList()
    {
        List<Age> list= new ArrayList<>();
        list.add(new Age("Age range"));
        list.add(new Age("6->10"));
        list.add(new Age("11->15"));
        list.add(new Age("16->20"));
        list.add(new Age("21->30"));
        return list;
    }
}