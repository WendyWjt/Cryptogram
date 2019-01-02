package jiangtongwang.cryptogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Delete extends AppCompatActivity
{
    // Google Firebase Database Reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // Event Listener that listens to each child in the database
    private ChildEventListener childEventListener;

    // Local data structure that will store all the values from the database
    private ArrayList<CryptoData> cryptoDataList;
    private ArrayList<CryptoData> searchResults;

    // ArrayAdapter allows the results to be displayed in a list on the app
    private CryptoDataAdapter listAdapter;

    // The selectedItem
    private CryptoData selectedItem;
    private TextView selected;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://lab5-b985e.firebaseio.com/cryptoData");


        // Set up an array that will have the contents that you want to display
        cryptoDataList = new ArrayList<CryptoData>();
        searchResults = new ArrayList<CryptoData>();
        selected = findViewById( R.id.textViewSelected );

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database

        childEventListener = new ChildEventListener()
        {
            @Override
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CryptoData c = dataSnapshot.getValue(CryptoData.class);
                listAdapter.add(c);
                cryptoDataList.add(c);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list adapter to read from the results array
        listAdapter = new CryptoDataAdapter(this, searchResults);
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);


        results.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // Get the selected item text from ListView
                selectedItem = (CryptoData) parent.getItemAtPosition(position);
                selected.setText( "Selected: " + selectedItem.getName() + " ." );
            }
        });

    }

    public void buttonDelete( View view )
    {
        if (selected.getText().toString().equals(""))
        {
            Toast.makeText(this, "You have not chosen any Cryptogram!", Toast.LENGTH_LONG).show();
        }
        else
        {
            selected.setText("");
            myRef.child(selectedItem.getUid()).removeValue();
            cryptoDataList.remove(selectedItem);
            listAdapter.clear();    // clears any content
            // refresh
            for ( CryptoData c : cryptoDataList)
            {
                listAdapter.add(c);
            }
        }
    }

    public void buttonSearch( View view )
    {
        listAdapter.clear();    // clears any content
        boolean found = false;
        EditText searchText = (EditText)findViewById(R.id.editTextSearch);
        String search = searchText.getText().toString();

        for( CryptoData c: cryptoDataList)
        {
            if( c.getName().contains( search ) || c.getDescription().contains( search ) )
            {
                // If there is a match, add the result to the listAdapter for display
                listAdapter.add(c);
                found = true;
            }
        }
        if(!found)
        {
            Toast.makeText(this, search + " not found.", Toast.LENGTH_LONG).show();
        }
        searchText.setText("");
    }


    public void buttonBack( View view )
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}
