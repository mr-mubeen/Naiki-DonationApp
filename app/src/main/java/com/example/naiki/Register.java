package com.example.naiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText uname , reg_pass , email , reg_phone ;
    TextView address;
    FusedLocationProviderClient fusedLocationProviderClient;
    ProgressBar progressBar;
    Button button;

    Spinner spinner;
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        uname = (EditText) findViewById(R.id.username_reg);
        reg_phone = (EditText) findViewById(R.id.phone_reg);
        address =(TextView)  findViewById(R.id.address_reg);
        email =(EditText)  findViewById(R.id.email_reg);
        reg_pass =(EditText)  findViewById(R.id.password_reg);
        spinner =  findViewById(R.id.type_reg);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.register);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource ( this, R.array.type , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

       getLocation();



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public void reg(View view){

        if (ActivityCompat.checkSelfPermission(Register.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(Register.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        if(uname.length()==0)
        {
            uname.requestFocus();
            uname.setError("FIELD CANNOT BE EMPTY");
        }

        else if(address.length()==0)
        {
            address.requestFocus();
            address.setError("FIELD CANNOT BE EMPTY");
            Toast.makeText(this, "Please enable Location to proceed", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(Register.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        else if(email.length()==0)
        {
            email.requestFocus();
            email.setError("FIELD CANNOT BE EMPTY");
        }
        else if(reg_pass.length()==0)
        {
            reg_pass.requestFocus();
            reg_pass.setError("FIELD CANNOT BE EMPTY");
        }
        else if(reg_phone.length()==0)
        {
            reg_phone.requestFocus();
            reg_phone.setError("FIELD CANNOT BE EMPTY");
        }

        else if (reg_phone.length()!=0 && reg_pass.length()!=0 && email.length()!=0 && uname.length()!=0 && address.length()!=0) {

            String user_text = uname.getText().toString();
            String pass_text = reg_pass.getText().toString();
            String address_text = address.getText().toString();
            String email_text = email.getText().toString();
            String tp = spinner.getSelectedItem().toString();
            String phone_text = reg_phone.getText().toString();

            progressBar.setVisibility(View.VISIBLE);
            button.setVisibility(View.INVISIBLE);

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+92" + reg_phone.getText().toString(),
                    60,
                    TimeUnit.SECONDS,
                    Register.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            progressBar.setVisibility(View.GONE);
                            button.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            progressBar.setVisibility(View.GONE);
                            button.setVisibility(View.VISIBLE);
                            Toast.makeText(Register.this , e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            progressBar.setVisibility(View.GONE);
                            button.setVisibility(View.VISIBLE);
                            Intent intent = new Intent(Register.this , OTP.class);
                            intent.putExtra("mobile" , reg_phone.getText().toString());

                            intent.putExtra("verificationId" , verificationId);


                            intent.putExtra("user_text" , uname.getText().toString());
                            intent.putExtra("pass_text" , reg_pass.getText().toString());
                            intent.putExtra("address_text" , address.getText().toString());
                            intent.putExtra("email_text" , email.getText().toString());
                            intent.putExtra("tp" , spinner.getSelectedItem().toString());
                            intent.putExtra("phone_text" , reg_phone.getText().toString());

                            startActivity(intent);
                        }
                    }
            );




        }
    }



    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null)
                {

                    try {
                        Geocoder geocoder = new Geocoder(Register.this , Locale.getDefault());
                        List<Address> address1 = geocoder.getFromLocation(location.getLatitude() , location.getLongitude() ,1);

                        address.setText(address1.get(0).getAddressLine(0));
//                        textView2 = findViewById(R.id.textView6);
//                        textView2.setText(Html.fromHtml("<font> Latitude : </font>"  +address.get(0).getCountryName()));
//                        textView3 = findViewById(R.id.textView7);
//                        textView3.setText(Html.fromHtml("<font> Latitude : </font>"  +address.get(0).getLocality()));
//                        textView4 = findViewById(R.id.textView8);
//                        textView4.setText(Html.fromHtml("<font> Latitude : </font>"  +address.get(0).getLatitude()));
//                        textView5 = findViewById(R.id.textView9);
//                        textView5.setText(Html.fromHtml("<font> Latitude : </font>"  +address.get(0).getLongitude()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
}
