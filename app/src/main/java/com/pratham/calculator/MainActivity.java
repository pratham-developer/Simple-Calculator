package com.pratham.calculator;

import android.content.Context;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.VibrationEffect;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;
import android.widget.EditText;
import android.widget.Button;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Vibrator vib;
    private TextView title;
    private EditText inp1;
    private EditText inp2;
    private Button bplus;
    private Button bminus;
    private Button bmul;
    private Button bdiv;
    private TextView out;
    private Button bpow;
    private Button brem;
    private Button clrbut;
    DecimalFormat way;

    public void solve(int i){
        vib.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
        String s1 = inp1.getText().toString().strip();
        String s2 = inp2.getText().toString().strip();
        if(s1.isEmpty() && s2.isEmpty()){
            Toast.makeText(getApplicationContext(),"both fields are empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(s1.isEmpty()){
            Toast.makeText(getApplicationContext(),"enter num 1",Toast.LENGTH_SHORT).show();
            return;
        }
        if(s2.isEmpty()){
            Toast.makeText(getApplicationContext(),"enter num 2",Toast.LENGTH_SHORT).show();
            return;
        }


            inp1.onEditorAction(EditorInfo.IME_ACTION_DONE);
            inp2.onEditorAction(EditorInfo.IME_ACTION_DONE);
            BigDecimal a,b;
            try {
                a=new BigDecimal(s1);
                b=new BigDecimal(s2);
            } catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(),"Invalid Numbers",Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal ans = BigDecimal.ZERO;
            switch (i){
                case 1:
                    ans = a.add(b);
                    break;

                case 2:
                    ans = a.subtract(b);
                    break;

                case 3:
                    ans = a.multiply(b);
                    break;

                case 4:
                    if(b.compareTo(BigDecimal.ZERO)==0){
                        Toast.makeText(getApplicationContext(),"denominator cannot be 0",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ans = a.divide(b,2,RoundingMode.HALF_UP);
                    break;

                case 5:
                   ans = a.pow(b.intValue());
                   break;
                case 6:
                    ans = a.remainder(b);
                    break;


            }
            out.setText(way.format(ans));


    }
    public void clearCommand(){
        vib.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
        inp1.setText("");
        inp2.setText("");
        out.setText("");
        inp1.requestFocus();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        way = new DecimalFormat("0.00");
        title = findViewById(R.id.title);
        inp1 = findViewById(R.id.num1);
        inp2 = findViewById(R.id.num2);
        bplus = findViewById(R.id.plusbut);
        bminus = findViewById(R.id.minusbut);
        bmul = findViewById(R.id.mulbut);
        bdiv = findViewById(R.id.divbut);
        out = findViewById(R.id.result);
        bpow=findViewById(R.id.powbut);
        brem=findViewById(R.id.rembut);
        clrbut=findViewById(R.id.clrb);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"built by Pratham Khanduja",Toast.LENGTH_SHORT).show();
                vib.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
            }
        });

       bplus.setOnClickListener(v->solve(1));
       bminus.setOnClickListener(v->solve(2));
       bmul.setOnClickListener(v->solve(3));
       bdiv.setOnClickListener(v->solve(4));
       bpow.setOnClickListener(v->solve(5));
       brem.setOnClickListener(v->solve(6));
       clrbut.setOnClickListener(v->clearCommand());



    }
}