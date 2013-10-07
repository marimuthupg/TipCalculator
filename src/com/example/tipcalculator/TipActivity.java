package com.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class TipActivity extends Activity {

	/**
	 * Slider control, avoids lookup.
	 */
	private SeekBar tipPercentControl = null;
	
	private TextView tipTV = null;
	
	private EditText amountET = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        
        //Get and cache controls 
        tipPercentControl = (SeekBar) findViewById(R.id.tipPerCentSeekBar);
        tipTV = (TextView) findViewById(R.id.tipValueTextView);
        amountET = (EditText) findViewById(R.id.amountEditText);
        
        //Add Change listener for slider control
        tipPercentControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

        	int progressChanged = 0;
 
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            	TextView tipTV = (TextView) findViewById(R.id.tipPercentTextView);
            	tipTV.setText(Integer.toString(progressChanged)+"%");
            	reconcileTip(seekBar,progressChanged);
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

    /**
     * Listener for 10 % button, invoked on click of 10% button
     * @param v
     */
    public void on10PercentClick(View v) {
    	tipPercentControl.setProgress(10);
    	reconcileTip(v,10);
    }

    /**
     * Listener for 15 % button, invoked on click of 15% button
     * @param v
     */
    public void on15PercentClick(View v) {
    	tipPercentControl.setProgress(15);
    	reconcileTip(v,15);
    }
    
    /**
     * Listener for 20 % button, invoked on click of 20% button
     * @param v
     */
    public void on20PercentClick(View v) {
    	tipPercentControl.setProgress(20);
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
    
    /**
     * Gets amount from user input. 
     * @param v
     * @return
     */
    public float getAmount(View v) {
    	
    	
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
    	if(tipTV !=null) {
    		tipTV.setText(Float.toString(tip));
    	}
    }
}
