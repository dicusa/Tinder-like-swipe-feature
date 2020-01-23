package com.attendancemanager;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.attendancemanager.databinding.ActivityMainBinding;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import adapter.StudentJsonAdapter;
import api.ApiInterface;
import modelclass.Student;
import modelclass.Student_;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.attendancemanager.R.menu.main_window_menu;


public class MainActivity extends AppCompatActivity {
Context context = MainActivity.this;
ActivityMainBinding view;
    private ArrayList<String>  al = new ArrayList<>();
    private ArrayList<String> bl = new ArrayList<>();
    private ArrayList<String> a2 = new ArrayList<>();
    private ArrayList<String> b2 = new ArrayList<>();
    public StudentJsonAdapter studentnames;
    private int i;
    Menu menu1;
    

    SwipeFlingAdapterView flingContainer;



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("")//url of firebase app
            .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
            .build();


    ApiInterface api = retrofit.create(ApiInterface.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = DataBindingUtil.setContentView(this,R.layout.activity_main);
       flingContainer = findViewById(R.id.framelayout);
       setActionBars();

       setList();
        setOnClickListner();
       callApi();

        setFrameCards();

    }

    private void setActionBars() {
        setSupportActionBar(view.cutomactionbar.toolbar);
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Attendance Manager");
    }

    //method to create menu option in action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(main_window_menu,menu);
menu1 =menu;
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_help :
                Log.e("Menu item","Help menu item selected");
                break;

            case R.id.menu_dark_mode:
                Log.e("Menu item","Dark mode menu item selected");
                view.parent.setBackgroundColor(Color.parseColor("#D80F0F0F"));
                view.framelayout.setBackgroundResource(R.drawable.main_frame_dark_bg);
                getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Attendance Manager </font>"));
                menu1.findItem(R.id.menu_dark_mode).setVisible(false);
                menu1.findItem(R.id.menu_light_mode).setVisible(true);
                break;

            case R.id.menu_light_mode:
                Log.e("Menu item","Light mode menu item selected");
                view.parent.setBackgroundColor(Color.parseColor("#B4EEEEEE"));
                view.framelayout.setBackgroundResource(R.drawable.main_frame_bg);
                getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Attendance Manager </font>"));
                menu1.findItem(R.id.menu_dark_mode).setVisible(true);
                menu1.findItem(R.id.menu_light_mode).setVisible(false);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setList() {

        al.add("Yash");
        al.add("Shubham");
        al.add("Shivangi");
        al.add("Rishabh");
        al.add("Udit");
        al.add("Madhur");
        al.add("Rajat");
        al.add("Puneet");

        a2.add("17EGJCS191");
        a2.add("17EGJCS192");
        a2.add("17EGJCS193");
        a2.add("17EGJCS194");
        a2.add("17EGJCS195");
        a2.add("17EGJCS196");
        a2.add("17EGJCS197");
        a2.add("17EGJCS198");

    }


    private void callApi() {

        view.progressbar.setVisibility(View.VISIBLE);

//get data from firebase with get function
        Call<Student> call2=api.getData();
        call2.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {

                 Response<Student> res = response;
                 ArrayList<Student_> info ;

                Log.e("Respone from Firebase",res.toString());
                Log.e("Respone from Firebase",res.body().toString());
             
                info =(ArrayList<Student_>) res.body().getStudent();
                for (int a =0;a<info.size();a++) {
                    bl.add(info.get(a).getName());
                    b2.add(info.get(a).getRollno());
                }
                studentnames.notifyDataSetChanged();
                view.progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                 Log.e("Failure","Firebase Response Failed");
            }
        });



    }

    private void setOnClickListner() {

        view.absentbutton.setOnClickListener(v->{
            if (view.progressbar.getVisibility() == View.VISIBLE)
            {

            }
else {
                Log.d("LIST", "removed object!");
                bl.remove(0);
                b2.remove(0);
                studentnames.notifyDataSetChanged();
                View view = flingContainer.getChildAt(0);
                flingContainer.removeViewsInLayout(0, flingContainer.getChildCount());
                if (bl.isEmpty()) {
                    updateLayout();

                }
            }
        });

        view.presentbutton.setOnClickListener(v->{
            if (view.progressbar.getVisibility() == View.VISIBLE)
            {

            }
            else {
                Log.d("LIST", "removed object!");
                bl.remove(0);
                b2.remove(0);
                studentnames.notifyDataSetChanged();
                View view = flingContainer.getChildAt(0);
                flingContainer.removeViewsInLayout(0, flingContainer.getChildCount());
                if (bl.isEmpty()) {
                    updateLayout();
                }
            }
        });
    }

    private void updateLayout() {
        view.presentbutton.setVisibility(View.GONE);
        view.absentbutton.setVisibility(View.GONE);
        view.framelayout.setVisibility(View.GONE);
        view.sendAttendance.setVisibility(View.VISIBLE);
        view.exit.setVisibility(View.VISIBLE);
    }


    private void setFrameCards() {





        studentnames = new StudentJsonAdapter(this, R.layout.slideviews, R.id.name, bl,b2 );
Log.e("Inside setFrameCard","Inside setFrameCards method");
        flingContainer.setAdapter(studentnames);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                bl.remove(0);
                b2.remove(0);
                studentnames.notifyDataSetChanged();
                if(bl.isEmpty())
                {
                    updateLayout();
                }
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here

            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener((itemPosition, dataObject) -> Toast.makeText(context, "OnClicked", Toast.LENGTH_SHORT).show());

    }





    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
