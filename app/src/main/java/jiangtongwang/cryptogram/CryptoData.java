package jiangtongwang.cryptogram;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CryptoData implements Serializable
{
    private Crypto crypto;
    private String description;
    private String name;
    private String uid;

    public CryptoData(){}

    public CryptoData(String name, String uid, String description,
                      String plaintext, String hint1, String hint2, String hint3 )
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date d = new Date();
        this.crypto = new Crypto( plaintext, hint1, hint2, hint3 );
        this.name = formatter.format(d) + ": " + name;
        this.uid = uid;
        this.description = description;
    }

    // Accessor
    public String getName()
    {
        return name;
    }

    public String getUid()
    {
        return uid;
    }

    public Crypto getCrypto()
    {
        return crypto;
    }

    public String getDescription()
    {
        return description;
    }

    public String toString()
    {
        return ( this.name + "( " + this.description + " )" );
    }


    // Mutator
    public void createCaesar( int key )
    {
        crypto.setCiphertext( Crypto.Caesar( crypto.getPlaintext(), key ) );
    }

    public void createCaesar_random()
    {
        crypto.setCiphertext( Crypto.Caesar_random( crypto.getPlaintext() ) );
    }


    public void createOneTimePad( long key )
    {
        crypto.setCiphertext( Crypto.OneTimePad( crypto.getPlaintext(), key ) );
    }

    public void createOneTimePad_random()
    {
        crypto.setCiphertext( Crypto.OneTimePad_random( crypto.getPlaintext() ) );
    }


    public void createSubstitition()
    {
        crypto.setCiphertext( Crypto.Substitution( crypto.getPlaintext() ) );
    }


    public void createAsk2()
    {
        crypto.setCiphertext( Crypto.Ask2( crypto.getPlaintext() ) );
    }


    public void createVigenere( String key )
    {
        crypto.setCiphertext( Crypto.Vigenere( crypto.getPlaintext(), key ) );
    }


}

