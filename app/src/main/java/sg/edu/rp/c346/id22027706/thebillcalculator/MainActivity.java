package sg.edu.rp.c346.id22027706.thebillcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amt;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    RadioGroup payment;
    Button split;
    Button reset;
    Button payNow;
    TextView bill;
    TextView pays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.amtInput);
        pax = findViewById(R.id.paxInput);
        svs = findViewById(R.id.buttonSVS);
        gst = findViewById(R.id.buttonGST);
        discount = findViewById(R.id.discountInput);
        payment = findViewById(R.id.rgPaymentMethod);
        split = findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);
        payNow = findViewById(R.id.radioButtonPaynow);
        bill = findViewById(R.id.textBill);
        pays = findViewById(R.id.textPays);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double finalAmt = 0.00;

                if (amt.getText().toString().length() != 0 && pax.getText().toString().length() != 0){
                    if (!svs.isChecked() && !gst.isChecked()){
                        finalAmt = Double.parseDouble(amt.getText().toString());
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        finalAmt = Double.parseDouble(amt.getText().toString()) * 1.07;
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        finalAmt = Double.parseDouble(amt.getText().toString()) * 1.1;
                    } else {
                        finalAmt = Double.parseDouble(amt.getText().toString()) * 1.17;
                    }

                    if (discount.getText().toString().length() != 0){
                        finalAmt = finalAmt * (1 - Double.parseDouble(discount.getText().toString()) / 100);
                    }

                    int people = Integer.parseInt(pax.getText().toString());
                    int checkedRadioId = payment.getCheckedRadioButtonId();
                    String response = "";
                    if (people > 1){
                        if (checkedRadioId == R.id.radioButtonPaynow){
                            response = "via PayNow to 912345678";
                        } else if (checkedRadioId == R.id.radioButtonCash){
                            response = "in cash";
                        }
                    }

                    double each = 0;
                    each = (finalAmt/people);

                    bill.setText("Total Bill: $" + String.format("%.2f", finalAmt));
                    pays.setText("Each Pays: $" + String.format("%.2f", each) + " " + response);
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amt.setText("");
                pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");

            }
        });
}}