package com.dyingtofall.helpmeupx;

/**
 * Created by ptsushima on 9/9/17.
 */

import android.app.Activity;

public class DisplayDatabase extends Activity {
  /*  int from_Where_I_Am_Coming = 0;
    private DatabaseHelper mydb ;

    TextView date ;
    TextView location;
    TextView time;
    //TextView street;
    //TextView place;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_database);
        date = (TextView) findViewById(R.id.editTextName);
        location = (TextView) findViewById(R.id.editTextPhone);
        time = (TextView) findViewById(R.id.editTextStreet);
        //street = (TextView) findViewById(R.id.editTextEmail);
        //place = (TextView) findViewById(R.id.editTextCity);

        mydb = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String dat = rs.getString(rs.getColumnIndex(DatabaseHelper.Event_COLUMN_DATE));
                String loca = rs.getString(rs.getColumnIndex(DatabaseHelper.Event_COLUMN_LOCATION));
                String tim = rs.getString(rs.getColumnIndex(DatabaseHelper.Event_COLUMN_TIME));

                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                date.setText((CharSequence)dat);
                date.setFocusable(false);
                date.setClickable(false);

                location.setText((CharSequence)loca);
                location.setFocusable(false);
                location.setClickable(false);

                time.setText((CharSequence)tim);
                time.setFocusable(false);
                time.setClickable(false);


            }
        }
    }




    */
}
