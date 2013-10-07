package com.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class TipActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        SeekBar tipPercentControl = (SeekBar) findViewById(R.id.tipPerCentSeekBar);
        
        tipPercentControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

        	int progressChanged = 0;
 
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            	TextView tipTV = (TextView) findViewById(R.id.tipPercentTextView);
            	tipTV.setText(Integer.toString(progressChanged)+"%");
            }
 
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
 
            public void onStopTrackingTouch(SeekBar seekBar) {
            	reconcileTip(seekBar,progressChanged);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip, menu);
        return true;
    }
    
    public void on10PercentClick(View v) {
    	reconcileTip(v,10);
    }

    public void on15PercentClick(View v) {
    	reconcileTip(v,15);
    }
    
    public void on20PercentClick(View v) {
    	reconcileTip(v,20);
    }
    
    /**
     * Reconciles tip for given perCent
     * @param v
     * @param perCent
     */
    public void reconcileTip(View v, float perCent) {
    	float amount = getAmount(v);
    	
    	float tip = calculateTip(amount, perCent);
    
    	updateTipValue(v, tip);
    }
    
    /**
     * Calculate and return tip based on the given percentage of tip
     * @param amount
     * @param perCent
     * @return tip
     */
    public float calculateTip(float amount, float perCent) {
    	
    	return amount * perCent / 100;
    	
    }
    
    public float getAmount(View v) {
    	EditText amountET = (EditText) findViewById(R.id.amountEditText);
    	
    	float amount = 0;
    	
    	if(amountET != null) {
    		String amountStr = amountET.getText().toString();
    		if(amountStr != null && ! amountStr.isEmpty()) {
    			amount = Float.parseFloat(amountStr);
    		}
    	}
    	
    	return amount;
    }

    /**
     * Update UI with given tip value
     * @param v
     * @param tip
     */
    public void updateTipValue(View v, float tip) {
    	TextView tipTV = (TextView) findViewById(R.id.tipValueTextView);
    	tipTV.setText(Float.toString(tip));
    }
}
