package com.example.friendgroup;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        updateView( );
    }

    // Build a View dynamically with all the friends
    public void updateView( ) {
        ArrayList<Friends> friends = dbManager.selectAll( );

        // create ScrollView and GridLayout
        if( friends.size( ) > 0 ) {

            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( friends.size( ) );
            grid.setColumnCount( 5 );

            // create arrays of components
            TextView [] ids = new TextView[friends.size( )];
            EditText [][] namesAndEmails = new EditText[friends.size( )][3];
            Button [] buttons = new Button[friends.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );
            int width = size.x;

            // create the TextView for the friend's id
            int i = 0;

            for ( Friends friend : friends ) {

                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + friend.getId( ) );

                // create the two EditTexts for the friend's name and email
                namesAndEmails[i][0] = new EditText( this );
                namesAndEmails[i][1] = new EditText( this );
                namesAndEmails[i][2] = new EditText( this );
                namesAndEmails[i][0].setText( friend.getFirstName( ) );
                namesAndEmails[i][1].setText( "" + friend.getLastName( ) );
                namesAndEmails[i][2].setText( "" + friend.getEmail( ) );
                namesAndEmails[i][1]
                        .setInputType( InputType.TYPE_CLASS_TEXT );
                namesAndEmails[i][0].setId( 10 * friend.getId( ) );
                namesAndEmails[i][1].setId( 10 * friend.getId( ) + 1 );
                namesAndEmails[i][2].setId( 10 * friend.getId( ) + 2 );

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( friend.getId( ) );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid
                grid.addView( ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmails[i][0], ( int ) ( width * .20 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmails[i][1], ( int ) ( width * .20 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmails[i][2], ( int ) ( width * .20 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .20 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }
            scrollView.addView( grid );
            setContentView( scrollView );
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // retrieve name and email of the friend
            int friendId = v.getId();
            EditText firstNameET = findViewById(10 * friendId);
            EditText lastNameET = findViewById(10 * friendId + 1);
            EditText emailET = findViewById(10 * friendId + 2);
            String firstName = firstNameET.getText().toString();
            String lastName = lastNameET.getText().toString();
            String emailString = emailET.getText().toString();

            // update friend in database
            try {
                dbManager.updateById( friendId, firstName, lastName, emailString );
                Toast.makeText(UpdateActivity.this, "Friend updated",
                        Toast.LENGTH_SHORT).show();

                // update screen
                updateView();
            } catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this,
                        "Format error", Toast.LENGTH_LONG).show();
            }
        }
    }
}
