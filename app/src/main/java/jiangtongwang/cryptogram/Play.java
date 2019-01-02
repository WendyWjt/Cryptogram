package jiangtongwang.cryptogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends AppCompatActivity {
    CryptoData played;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        played = (CryptoData)intent.getSerializableExtra("selected");

        TextView puzzle = findViewById( R.id.textViewPuzzle );
        puzzle.setText( played.getCrypto().getCiphertext() );
    }

    public void buttonCheck( View view )
    {
        EditText textAnswer = findViewById( R.id.editTextAnswer);
        String answer = textAnswer.getText().toString();

        if ( answer.equals("") )
        {
            Toast.makeText( this, "Answer cannot be empty string.", Toast.LENGTH_LONG ).show();
        }
        else
        {
            if (answer.equals( played.getCrypto().getPlaintext() ) )
            {
                Toast.makeText( this, "Bingo! You have solved this puzzle! ", Toast.LENGTH_LONG ).show();
            }
            else
            {
                Toast.makeText( this, "Nope! You enter the wrong answer! ", Toast.LENGTH_LONG ).show();
            }
        }
    }

    public void buttonHint1( View view )
    {
        Toast.makeText( this, "Hint1: " + played.getCrypto().getHint1(), Toast.LENGTH_LONG ).show();
    }

    public void buttonHint2( View view )
    {
        Toast.makeText( this, "Hint2: " +played.getCrypto().getHint2(), Toast.LENGTH_LONG ).show();
    }

    public void buttonHint3( View view )
    {
        Toast.makeText( this, "Hint3: " +played.getCrypto().getHint3(), Toast.LENGTH_LONG ).show();
    }

    public void buttonBack( View view )
    {
        Intent intent = new Intent( this, DisplayPlaylist.class);
        startActivity(intent);
    }
}
