package com.example.ivo.quadratic_equation;

import android.content.Context;

import java.text.DecimalFormat;

/**
 * Created by ivo on 5/30/2018.
 */

public class Servise extends  Repository {
    public Servise(Context context) {
        super(context);
    }

    public boolean AddInformation()
    {
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        Model model = new Model();
        model.a = String.valueOf(quadratic_equation.a);
        model.b = String.valueOf(quadratic_equation.b);
        model.c = String.valueOf(quadratic_equation.c);
        model.d = String.valueOf(decimalFormat.format(Result.d));
        model.x1 =String.valueOf(decimalFormat.format(Result.x1));
        model.x2 =String.valueOf(decimalFormat.format(Result.x2));
        return AddData(model);
    }
}
