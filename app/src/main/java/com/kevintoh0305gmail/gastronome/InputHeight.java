package com.kevintoh0305gmail.gastronome;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputHeight extends AppCompatActivity {
    EditText txtHeight;
    Button btnNext, btnBack;
    TextView tvError, tvUnit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_height);
        txtHeight = findViewById(R.id.etHeight);
        tvError = findViewById(R.id.tvHeightError);
        tvUnit = findViewById(R.id.tvHeightUnit);
        btnBack = findViewById(R.id.btnHeightBack);
        btnNext = findViewById(R.id.btnHeightNext);
        btnNext.setBackground(btnNext.getContext().getResources().getDrawable(R.drawable.next_button_inactive));
        // Set unit to inch if imperial units are chosen
        if(HelloPage.profile.getUnit().equals("imperial"))
        {
            tvUnit.setText("Inches");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputHeight.this, InputUnit.class));
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHeightValid()) {
                    //Converts to inches if imperial is chosen
                    if (tvUnit.getText().equals("Inches"))
                        HelloPage.profile.setHeight(Double.parseDouble(txtHeight.getText().toString()) * 2.54);
                    else
                        HelloPage.profile.setHeight(Double.parseDouble(txtHeight.getText().toString()));
                    startActivity(new Intent(InputHeight.this, InputWeight.class));
                }
            }
        });
        txtHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isHeightValid()) {
                    btnNext.setBackground(btnNext.getContext().getResources().getDrawable(R.drawable.next_button_active));
                }
                else {
                    btnNext.setBackground(btnNext.getContext().getResources().getDrawable(R.drawable.next_button_inactive));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public boolean isHeightValid(){
        if (txtHeight.getText().toString().isEmpty()){
            tvError.setText("Please enter your height.");
            return false;
        }
        else
        {
            double height = Double.parseDouble(txtHeight.getText().toString().trim());
            if (height > 0 && height < 300){
                tvError.setText("");
                return true;
            }
            else{
                tvError.setText("Please enter a valid height.");
                return false;
            }
        }
    }

}
