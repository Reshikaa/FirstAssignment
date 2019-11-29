package com.reshika.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
TextView tvCheckin,tvCheckout,tvResult1,tvResult2,tvResult3,tvResult4;
Spinner spinCountry,spinRoom;
EditText etRoom,etname,etAdult,etChildren;
AutoCompleteTextView autoCompleteTextView;
int turn=0;
int day1,month1,year1,day2,month2,year2;

    String rooms[]={"Deluxe","Platinum","Presidential"};
Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname=findViewById(R.id.etname);
        etAdult=findViewById(R.id.etAdult);
        etChildren=findViewById(R.id.etChildren);
        spinCountry=findViewById(R.id.spinCountry);
        tvCheckin=findViewById(R.id.tvCheckin);
        tvCheckout=findViewById(R.id.tvCheckout);
        tvResult1=findViewById(R.id.tvResult1);
        tvResult2=findViewById(R.id.tvResult2);
        tvResult3=findViewById(R.id.tvResult3);
        tvResult4=findViewById(R.id.tvResult4);
        etRoom=findViewById(R.id.etRoom);
        spinRoom=findViewById(R.id.spinRoom);
        btnCalculate=findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etname.getText())){
                    etname.setError("Please enter your name");
                    return;
                }else if(TextUtils.isEmpty(etAdult.getText()))
                {
                    etAdult.setText("Please enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(etChildren.getText()))
                {
                    etChildren.setText("Please enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(etRoom.getText()))
                {
                    etRoom.setText("Please enter number of room");
                    return;
                }
                else if(TextUtils.isEmpty(tvCheckin.getText())){
                    tvCheckin.setText("Please enter check in Date");
                    return;
                }
                else if(TextUtils.isEmpty(tvCheckout.getText())){
                    tvCheckout.setText("Please enter check out Date");
                    return;
                }

                //calculating days

                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(year1,month1,day1);
                cal2.set(year2,month2,day2);
                long milis1= cal1.getTimeInMillis();
                long milis2 = cal2.getTimeInMillis();
                long diff = milis1-milis2;
                long diffDays=(diff / (86400000));

                int numRoom = Integer.parseInt(etRoom.getText().toString());


                //calculate net total

                double roomprice, totalprice;
                double vat, servicecharge, nettotal;

                String roomtype = spinRoom.getSelectedItem().toString();


                if(roomtype=="Deluxe") {
                    roomprice = 2500;
                    totalprice = roomprice * numRoom * diffDays;
                    vat = (0.13 * totalprice) + totalprice;
                    nettotal = servicecharge = (0.10 * vat) + vat;
                    tvResult1.setText("Total cost is:" + totalprice);
                    tvResult2.setText("Price after VAT inclusion:" + vat);
                    tvResult3.setText("Price after service charge inclusion:" + servicecharge);
                    tvResult4.setText("Overall Price:" + nettotal);
                }
                else if(roomtype=="Platinum"){
                    roomprice=4000;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    tvResult1.setText("Total cost is:" +totalprice);
                    tvResult2.setText("Price after VAT inclusion:" +vat);
                    tvResult3.setText("Price after service charge inclusion:" + servicecharge);
                    tvResult4.setText("Overall Price:" +nettotal);
                }

                else if(roomtype=="Presedential"){
                    roomprice=7000;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    tvResult1.setText("Total cost is:" +totalprice);
                    tvResult2.setText("Price after VAT inclusion:" +vat);
                    tvResult3.setText("Price after service charge inclusion:" + servicecharge);
                    tvResult4.setText("Overall Price:" +nettotal);
                }



                    if(year1>year2)
                {
                    Toast.makeText(getApplicationContext(),"Invalid date for checkout",Toast.LENGTH_SHORT).show();
                }
                if(year1==year2 && month1>month2)
                {
                    Toast.makeText(getApplicationContext(),"Invalid date for checkout",Toast.LENGTH_SHORT).show();
                }
                if(year1==year2 && month1==month2 && day1>day2)
                {
                    Toast.makeText(getApplicationContext(),"Invalid date for checkout",Toast.LENGTH_SHORT).show();
                }

            }
        });

        ////spinner
        String countries[]={"Nepal","India","China","UK","US"};
        ArrayAdapter adapter= new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                countries
        );
        spinCountry.setAdapter(adapter);



        ArrayAdapter ad= new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                rooms
        );
        spinRoom.setAdapter(ad);

        tvCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn=1;
                loadDatePicker();
            }
        });

        tvCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn=2;
                loadDatePicker();
            }
        });

    }


    private void loadDatePicker(){
        final Calendar c= Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(
                this, this,year,month,day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month=month+1;
        String date="Month/Day/Year:" + month + "/" + dayOfMonth + "/" + year;
        if(turn==1)
        {
            day1=dayOfMonth;
            month1=month;
            year1=year;
            tvCheckin.setText(date);
        }
        if(turn==2)
        {
            day2=dayOfMonth;
            month2=month;
            year2=year;
            tvCheckout.setText(date);
        }
    }
}
