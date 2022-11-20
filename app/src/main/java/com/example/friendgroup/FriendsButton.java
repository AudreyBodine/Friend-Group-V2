package com.example.friendgroup;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

public class FriendsButton extends AppCompatButton {
    private Friends friend;

    public FriendsButton(Context context, Friends newTask ) {
        super( context );
        friend = newTask;
    }
}
