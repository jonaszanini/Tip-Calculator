package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentageFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double percentage = 0.15;
    private TextView amountTextView;
    private TextView percentageTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar percentageSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = findViewById(R.id.tvw_Amount);
        percentageTextView = findViewById(R.id.tvw_Percentage);
        tipTextView = findViewById(R.id.tvw_Tip);
        totalTextView = findViewById(R.id.tvw_Total);
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        final EditText amountEditText = findViewById(R.id.tvw_Amount);

        SeekBar percentSeekBar = findViewById(R.id.sbr_Percentage);

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentage = progress / 100;
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try{
                    billAmount = Double.parseDouble(s.toString()) / 100;
                    amountTextView.setText(currencyFormat.format(billAmount));
                }
                catch ( NumberFormatException e){
                    amountEditText.setText("");
                    billAmount = 0.0;
                }

                calculate();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void calculate(){
        percentageTextView.setText(percentageFormat.format(percentage));

        double tip = billAmount * percentage;
        double total = billAmount + tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

}
