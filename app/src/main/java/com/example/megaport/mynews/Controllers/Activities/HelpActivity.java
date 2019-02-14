package com.example.megaport.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import com.example.megaport.mynews.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.help_activity );

        ImageButton imageButton = findViewById( R.id.image_button_help );

        imageButton.setOnClickListener( view -> startActivity() );
    }

    private void startActivity(){
        Intent intent = new Intent( this, MainActivity.class );
        startActivity(intent);
    }
}