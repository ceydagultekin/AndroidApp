package com.example.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.health.connect.datatypes.units.Percentage;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class Page3 extends AppCompatActivity {

    private static final int BAR_COUNT=15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Random");
        }

        final EditText barCountEditText=findViewById(R.id.barCount);
        Button barCountButton=findViewById(R.id.barCountButton);
        final LinearLayout container=findViewById(R.id.container);


        barCountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    int barCount=Integer.parseInt(barCountEditText.getText().toString());

                    container.removeAllViews();

                    for (int i=0;i<barCount;i++){
                        ProgressBar progressBar=new ProgressBar(Page3.this,null, android.R.attr.progressBarStyleHorizontal);
                        progressBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


                        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

                        params.setMargins(0,26,0,0);
                        progressBar.setLayoutParams(params);

                        Random random = new Random();
                        int min=random.nextInt(100);
                        int max=random.nextInt(100);

                        int range=Math.abs(max-min);
                        min=Math.min(min,max);
                        max=min+range;


                        int randomValue= random.nextInt(range)+min;
                        progressBar.setMax(range);

                        progressBar.setProgress(randomValue-min);

                        TextView values=new TextView(Page3.this);
                        values.setLayoutParams(params);
                        values.setText(String.format("Min:%d,Max:%d,Value:%d,Percentage:%d%%",min,max,randomValue,(int)((randomValue-min)/(float) range*100)));
                        container.addView(values);

                        container.addView(progressBar);
                    }
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}