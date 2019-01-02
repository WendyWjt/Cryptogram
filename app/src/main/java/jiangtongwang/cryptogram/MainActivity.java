package jiangtongwang.cryptogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonCreate( View view )
    {
        Intent intent = new Intent( this, Create.class);
        startActivity(intent);
    }

    public void buttonPlay( View view )
    {
        Intent intent = new Intent( this, DisplayPlaylist.class);
        startActivity( intent );
    }

    public void buttonDelete( View view )
    {
        Intent intent = new Intent( this, Delete.class);
        startActivity( intent );
    }

}
