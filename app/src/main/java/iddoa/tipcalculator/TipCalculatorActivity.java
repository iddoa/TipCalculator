package iddoa.tipcalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class TipCalculatorActivity extends AppCompatActivity {
    /**
     * A function that receives a string representing the bill given
     * by the user and returns a string representing the tip of 12%
     * (and a dollar sign)
     */
    String CalcTip(String billString)
    {
        // checkbox of rounding
        CheckBox cb = (CheckBox) findViewById(R.id.chkRound);
        double numVal = Double.parseDouble(billString);
        numVal = numVal * 0.12;
        //checking if rounding up is needed
        if (cb.isChecked() == true) {
            // i added the 0.5 to make sure it rounds up like requested rather than the default round
            numVal = Math.ceil(numVal);
        }
        // string with 2 digits after decimal point (more wont mean anything talking about dollars)
        String result = String.format("%.2f", numVal);
        return "$ " + result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tip_calculator);
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        final Button b = (Button)
                findViewById(R.id.btnCalculate);
        final android.widget.TextView tip = (android.widget.TextView)
                findViewById(R.id.txtTipResult);
        b.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // getting the number as a string
                        EditText editTextName = (EditText) findViewById(R.id.edtBillAmount);
                        String numInput = editTextName.getText().toString();
                        // hiding the keyboard after click
                        if (v != null) {
                            android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                        if (!numInput.matches("")) {
                            tip.setText(CalcTip(numInput));
                        }
                        else {   //if nothing was given and calculate was clicked, an error message pops
                            dlgAlert.setMessage("Please enter a number");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                            dlgAlert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                        }
                    }
                });
    }
}
