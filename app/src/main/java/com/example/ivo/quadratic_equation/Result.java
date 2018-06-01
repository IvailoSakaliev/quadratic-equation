package com.example.ivo.quadratic_equation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Result extends AppCompatActivity {

    public static double d;
    public static double x1;
    public static double x2;
    private TextView textEquation;
    private  TextView textD;
    private  TextView textX1;
    private  TextView textX2;
    private Button btnNewSum;
    public Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        db = new Database(this);
        setAvtions();
        Sum();
        updateScreen();
        AddDataInDB();

        btnNewSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newSum();
            }
        });

    }

    private void AddDataInDB() {
       Servise servise = new Servise(this);
       if (servise.AddInformation())
       {
           Toast.makeText(Result.this, "Inserted data",Toast.LENGTH_LONG).show();
       }
        else
       {
           Toast.makeText(Result.this, "Error",Toast.LENGTH_LONG).show();
       }
    }

    private void setAvtions() {
        textD = (TextView)findViewById(R.id.textD);
        textX1 = (TextView)findViewById(R.id.textX1);
        textX2 = (TextView)findViewById(R.id.textX2);
        btnNewSum = (Button)findViewById(R.id.btnNewSum);
    }

    private void updateScreen() {
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
       textD.setText(String.valueOf(decimalFormat.format(d)));
       textX1.setText(String.valueOf(decimalFormat.format(x1)));
       textX2.setText(String.valueOf(decimalFormat.format(x2)));
    }
    private void newSum()
    {
        Intent intent = new Intent(Result.this,quadratic_equation.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }
    private void Sum()
    {
        d = (quadratic_equation.b * quadratic_equation.b) - (4 * quadratic_equation.a * quadratic_equation.c);
        x1 = (-quadratic_equation.b + Math.sqrt(d)) / (2 * quadratic_equation.a);
        x2 = (-quadratic_equation.b - Math.sqrt(d)) / (2 * quadratic_equation.a);
    }



}
