package com.example.friendgroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView = null;
    private int buttonWidth;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = findViewById( R.id.toolbar );
       // setSupportActionBar( toolbar );
        dbManager = new DatabaseManager(this);
        scrollView = findViewById(R.id.scrollView);
        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        buttonWidth = size.x / 2;
        updateView();
    }

    protected void onResume( ) {
        super.onResume( );
        updateView( );
    }

    public void updateView( ) {
        ArrayList<Friends> friends = dbManager.selectAll();
        if (friends.size() > 0) {
            // remove subviews inside scrollView if necessary
            scrollView.removeAllViewsInLayout();

            // set up the grid layout
            GridLayout grid = new GridLayout(this);
            grid.setRowCount((friends.size() + 1) / 2);
            grid.setColumnCount(2);

            // create array of buttons, 2 per row
            FriendsButton[] buttons = new FriendsButton[friends.size()];
            //ButtonHandler bh = new ButtonHandler( );

            // fill the grid
            int i = 0;
            for (Friends friend : friends) {
                // create the button
                buttons[i] = new FriendsButton(this, friend);
                buttons[i].setText(friend.getFirstName() + "\n" + friend.getLastName() + "\n" + friend.getEmail());

                // add the button to grid
                grid.addView(buttons[i], buttonWidth,
                        GridLayout.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_add:
                Log.w("MainActivity", "Add Selected");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete Selected");
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                Log.w("MainActivity", "Update Selected");
                Intent updateIntent = new Intent( this, UpdateActivity.class );
                this.startActivity(updateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}