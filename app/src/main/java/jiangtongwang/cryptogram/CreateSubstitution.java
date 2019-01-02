package jiangtongwang.cryptogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateSubstitution extends AppCompatActivity {
    // Google Firebase Database References
    private DatabaseReference myRef;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_substitution);
        // Initializes the references to the database and cryptoDatas
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://lab5-b985e.firebaseio.com/cryptoData");
    }

    public void buttonAdd( View view )
    {
        // Get values
        EditText editName = findViewById(R.id.editTextName);
        String name = editName.getText().toString();

        EditText editDescription = findViewById(R.id.editTextDescription);
        String description = editDescription.getText().toString();

        EditText editPlaintext = findViewById(R.id.editTextPlaintext);
        String plaintext = editPlaintext.getText().toString();

        EditText editHint1 = findViewById(R.id.editTextHint1);
        String hint1 = editHint1.getText().toString();

        EditText editHint2 = findViewById(R.id.editTextHint2);
        String hint2 = editHint2.getText().toString();

        EditText editHint3 = findViewById(R.id.editTextHint3);
        String hint3 = editHint3.getText().toString();


        // Add values
        if ( !plaintext.equals("") )
        {
            String uidKey = myRef.push().getKey(); // Generates unique random key
            CryptoData c = new CryptoData(name, uidKey, description, plaintext, hint1, hint2, hint3);
            c.createSubstitition();

            myRef.child(uidKey).setValue(c);   // Adds new Contact to the Database
            Toast.makeText(this, "Cryptogram " + c.getName() + " is successfully added.", Toast.LENGTH_LONG).show();

            // Resets fields
            editName.setText("");
            editDescription.setText("");
            editPlaintext.setText("");
            editHint1.setText("");
            editHint2.setText("");
            editHint3.setText("");
        }
        else
        {
            Toast.makeText(this, "Plaintext cannot be empty string.", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonBack( View view)
    {
        Intent intent = new Intent( this, Create.class);
        startActivity(intent);
    }
}
