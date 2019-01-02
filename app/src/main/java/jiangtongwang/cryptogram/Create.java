package jiangtongwang.cryptogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    public void buttonCaesar( View view)
    {
        Intent intent = new Intent( this, CreateCaesar.class);
        startActivity(intent);
    }

    public void buttonSubstitution( View view)
    {
        Intent intent = new Intent( this, CreateSubstitution.class);
        startActivity(intent);
    }

    public void buttonAsk2( View view)
    {
        Intent intent = new Intent( this, CreateAsk2.class);
        startActivity(intent);
    }

    public void buttonVigenere( View view)
    {
        Intent intent = new Intent( this, CreateVigenere.class);
        startActivity(intent);
    }

    public void buttonOneTimePad( View view)
    {
        Intent intent = new Intent( this, CreateOneTimePad.class);
        startActivity(intent);
    }

    public void buttonBack( View view )
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}
