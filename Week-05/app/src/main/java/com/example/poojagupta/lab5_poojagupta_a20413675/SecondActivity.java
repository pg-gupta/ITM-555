package com.example.poojagupta.lab5_poojagupta_a20413675;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Pooja Gupta
 *         Date: 02/23/2018
 *         Assignment/HW: #5
 */
public class SecondActivity extends AppCompatActivity {

    int i = 0;
    int duration;
    Handler handler;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // get reference of views from the second activity
        final TextView cityNameTxt = (TextView) findViewById(R.id.cityNameTxtView);
        final TextView cityDescTxt = (TextView) findViewById(R.id.cityDescTxtView);
        final ImageView cityImage = (ImageView) findViewById(R.id.cityImageView);
        // get the value of seekbar through bundle previously set from main activity
        Bundle bundle = getIntent().getExtras();
        duration = bundle.getInt("Duration") * 1000;


        // cities Names
        final String[] citiesName = {"Banglore", "Hyderabad", "Mumbai", "Pune", "Gwalior", "Agra", "Delhi", "Jaipur", "Lucknow", "Kolkatta", "Indore", "Vadodara"};
        // cities Description
        final String[] citiesDescrition = {"I visited this place in 2013.",
                "I went with my family to this place in 2009.This is known as the pearl city.",
                "I used to visit this city many times, mostly on weekends during my job.",
                "I have worked here for about 4 years starting from 2013.",
                "This is my hometown i have spent around 24 year of my life here.",
                "I visited this place with school friends in 2008 to see Taj Mahal.",
                "I went to the city many times",
                "Would love visit the pink city in near future",
                "I am planning to explore this city this year",
                "I visited this place in my early childhood",
                "I went to this city in 2013 for my first job interview",
                "I wanted to visit this city last year but the plan got cancelled"
        };
        // cities image references
        final Integer[] imagesId = {R.drawable.banglore, R.drawable.hyderabad,
                R.drawable.mumbai, R.drawable.pune, R.drawable.gwalior,
                R.drawable.agra, R.drawable.delhi, R.drawable.jaipur,
                R.drawable.lucknow, R.drawable.kolkata, R.drawable.indore,
                R.drawable.vadodara};


        handler = new Handler();

        try {
            // show images at a time interval running continuously
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (i == citiesName.length - 1) i = 0;
                    index = i + 1;
                    cityNameTxt.setText(citiesName[i]);
                    cityDescTxt.setText(citiesDescrition[i]);
                    cityImage.setImageResource(imagesId[i]);
                    cityImage.setContentDescription(String.format("This is {0} city image", citiesName[i]));
                    Toast.makeText(getApplicationContext(), "Index " + index, Toast.LENGTH_SHORT).show();
                    handler.postDelayed(this, duration);
                    i++;


                }
            }, 0);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something Went Wrong!",Toast.LENGTH_SHORT).show();
        }
    }

    // stop the handler when back button is pressed
    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }
}
