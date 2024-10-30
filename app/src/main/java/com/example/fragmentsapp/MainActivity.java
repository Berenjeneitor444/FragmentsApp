package com.example.fragmentsapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug","onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d("debug","setOnApplyWindowInsetsListener");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main
                ), (v, insets) -> {
            Log.d("debug","setOnApplyWindowInsetsListener");
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            Log.d("debug","Insets: " + insets);
            return insets;
        });

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d("debug","Landscape");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedorA, new FragmentA())
                    .addToBackStack(null)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.contenedorB, new FragmentB())
                    .commit();
        }
        else {
        Button fragmentAButton = findViewById(R.id.fragment_a_button);
        fragmentAButton.setOnClickListener( v -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FragmentA())
                .addToBackStack(null)
                .commit());

        Button fragmentBButton = findViewById(R.id.fragment_b_button);
        fragmentBButton.setOnClickListener( v -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FragmentB())
                .addToBackStack(null)
                .commit());
        }
    }
}