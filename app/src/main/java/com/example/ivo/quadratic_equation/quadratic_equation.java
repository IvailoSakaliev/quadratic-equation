package com.example.ivo.quadratic_equation;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class quadratic_equation extends AppCompatActivity {

    public static double a;
    public static double b;
    public static double c;
    public static EditText textA;
    public static EditText textB;
    public static EditText textC;
    private Button btnSum;
    private Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic_equation);

        repo = new Repository(this);
        setActions();

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AddNumbers();
                    ClearField();
                    GoToResultActivity();
                }
                catch (NumberFormatException ex)
                {
                    Toast.makeText(quadratic_equation.this, "Please enter corect information", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setActions() {
        btnSum = (Button)findViewById(R.id.sum);
        textA = (EditText)findViewById(R.id.inputA);
        textB = (EditText)findViewById(R.id.inputB);
        textC = (EditText)findViewById(R.id.inputC);
    }
    private void AddNumbers()
    {
            a = Integer.parseInt(textA.getText().toString());
            b = Integer.parseInt(textB.getText().toString());
            c = Integer.parseInt(textC.getText().toString());
    }
    private void ClearField()
    {
        textA.setText("");
        textB.setText("");
        textC.setText("");
    }
    private void GoToResultActivity()
    {
        Intent intent = new Intent(quadratic_equation.this, Result.class);
        startActivity(intent);
    }
    public void GettAllScore(View v)
    {
        Cursor res = repo.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("A :"+ res.getString(1)+"\n");
            buffer.append("B :"+ res.getString(2)+"\n");
            buffer.append("C :"+ res.getString(3)+"\n");
            buffer.append("D :"+ res.getString(4)+"\n");
            buffer.append("X1 :"+ res.getString(5)+"\n");
            buffer.append("X2 :"+ res.getString(6)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());

    }

    private void showMessage(String title, String messege) {
        AlertDialog alertDialog = new AlertDialog.Builder(quadratic_equation.this).create();
        alertDialog.setCancelable(true);
        alertDialog.setTitle(title);
        alertDialog.setMessage(messege);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Clear",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Repository repo = new Repository(quadratic_equation.this);
                        repo.DeleteAll();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE , "Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whitch)
                    {
                        dialog.dismiss();
                    }
                }
        );
        alertDialog.show();
    }
}
