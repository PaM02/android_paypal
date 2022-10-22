package com.example.androidpaypallsdk.activities;

import  androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import com.example.androidpaypallsdk.R;
import org.json.JSONException;
import org.json.JSONObject;


public class PaymentDetails extends AppCompatActivity {

    TextView txtId,txtAmount,txtStatus;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtAmount = findViewById(R.id.txtAmount);
        txtId = findViewById(R.id.txtId);
        txtStatus = findViewById(R.id.txtStatus);

        Intent intent = getIntent();

        try {

            JSONObject jsonObject = new JSONObject((intent.getStringExtra("PaymentDetails")));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void showDetails(JSONObject response, String paymentAmount) {

        try {
            txtId.setText(response.getString("id"));
            txtStatus.setText(response.getString("state"));
            txtAmount.setText("$"+paymentAmount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
