package jiangtongwang.cryptogram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


public class Crypto implements Serializable
{
    private String plaintext;
    private String ciphertext;
    private String hint1;
    private String hint2;
    private String hint3;


    public Crypto(){}


    public Crypto( String plaintext, String hint1, String hint2, String hint3 )
    {
        this.plaintext = plaintext;
        this.ciphertext = "";
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.hint3 = hint3;
    }

    // Mutator
    public void setCiphertext( String ciphertext )
    {
        this.ciphertext = ciphertext;
    }


    // Accessor
    public String getPlaintext()
    {
        return this.plaintext;
    }

    public String getCiphertext()
    {
        return this.ciphertext;
    }

    public String getVigenereDefaultKey()
    {
        return "CRYPTO";
    }

    public String getHint1()
    {
        return this.hint1;
    }

    public String getHint2()
    {
        return this.hint2;
    }

    public String getHint3()
    {
        return this.hint3;
    }

    // Compute ciphertext
    public static String Caesar( String plaintext, int key )
    {
        String ciphertext = "";
        for ( int i = 0; i < plaintext.length(); i++ )
        {
            int ask2 = (int)plaintext.charAt(i);
            if ( ( ask2 <= 90 ) && ( ask2 >= 65 ) )
            {
                // upper-case letters
                if ( ask2 + key <= 90 )
                {
                    ask2 = ask2 + (key % 26);
                }
                else
                {
                    ask2 = ask2 - 26 + (key % 26);
                }
            }
            else if ( ( ask2 <= 122 ) && ( ask2 >= 97 ) )
            {
                // lower-case letters
                if ( ask2 + key <= 122 )
                {
                    ask2 = ask2 + (key % 26);
                }
                else
                {
                    ask2 = ask2 - 26 + (key % 26);
                }
            }
            ciphertext += (char)ask2;
        }
        return ciphertext;
    }


    public static String Caesar_random( String plaintext )
    {
        int shiftKey = (int)( Math.random() * ( 26 - 1 + 1 ) );
        return Crypto.Caesar( plaintext, shiftKey );
    }


    public static String Substitution( String plaintext )
    {
        String letter[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArrayList<String> upperLetter = new ArrayList<String>();
        for ( String s : letter )
        {
            upperLetter.add( s );
        }
        Collections.shuffle(upperLetter);

        String ciphertext = "";
        for ( int i = 0; i < plaintext.length(); i++ )
        {
            int ask2 = (int)plaintext.charAt(i);
            if ( ( ask2 <= 90 ) && ( ask2 >= 65 ) )
            {
                // upper-case letters
                int index = ask2 - 65;
                ciphertext += upperLetter.get(index);
            }
            else if ( ( ask2 <= 122 ) && ( ask2 >= 97 ) )
            {
                // lower-case letters
                int index = ask2 - 97;
                ciphertext += upperLetter.get(index).toLowerCase();
            }
        }
        return ciphertext;
    }


    public static String Ask2( String plaintext )
    {
        String ciphertext = "";
        for ( int i = 0; i < plaintext.length(); i++ )
        {
            int ask2 = (int)plaintext.charAt(i);
            if ( ( ask2 <= 90 ) && ( ask2 >= 65 ) )
            {
                // upper-case letters
                ciphertext += Crypto.binaryReverse( Crypto.decimalToBinary( ask2+32, 8 ) );
            }
            else if ( ( ask2 <= 122 ) && ( ask2 >= 97 ) )
            {
                // lower-case letters
                ciphertext += Crypto.decimalToBinary( ask2, 8 );
            }
        }
        return ciphertext;
    }

    public static String decimalToBinary( long decimal, int length )
    {
        String binary = "";
        long div = decimal;
        long mod = 0;
        while ( div != 0 )
        {
            mod = div % 2;
            div = div / 2;
            binary = mod + binary;
        }
        int digits = binary.length();
        if ( digits < length )
        {
            for ( int i = 0; i < ( length - digits ); i++ )
            {
                binary = "0" + binary;
            }
        }
        return binary;
    }

    public static String binaryReverse( String binary )
    {
        String reverse = "";
        for ( int i = 0; i < binary.length(); i++ )
        {
            if ( binary.charAt(i) == '1' )
            {
                reverse += "0";
            }
            else
            {
                reverse += "1";
            }
        }

        return reverse;
    }


    public static String Vigenere( String plaintext, String key )
    {
        String ciphertext = "";
        String letter[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArrayList<String> upperLetter = new ArrayList<String>();
        for ( String s : letter )
        {
            upperLetter.add( s );
        }


        for ( int i = 0; i < plaintext.length(); i++ )
        {
            int ask2 = (int)plaintext.charAt(i);
            int correspondingShiftIndex = i % key.length();

            int shiftKey = upperLetter.indexOf( Character.toString( key.charAt(correspondingShiftIndex) ) ) + 1;

            if ( ( ask2 <= 90 ) && ( ask2 >= 65 ) )
            {
                // upper-case letters
                if ( ask2 + shiftKey <= 90 )
                {
                    ask2 = ask2 + (shiftKey % 26);
                }
                else
                {
                    ask2 = ask2 - 26 + (shiftKey % 26);
                }
            }
            else if ( ( ask2 <= 122 ) && ( ask2 >= 97 ) )
            {
                // lower-case letters
                if ( ask2 + shiftKey <= 122 )
                {
                    ask2 = ask2 + (shiftKey % 26);
                }
                else
                {
                    ask2 = ask2 - 26 + (shiftKey % 26);
                }
            }
            ciphertext += (char)ask2;
        }

        return ciphertext;
    }

    public static String OneTimePad( String plaintext, long key )
    {
        String ciphertext = "";
        String binaryPlaintext = Ask2( plaintext );

        String exclusiveOrKey = Crypto.decimalToBinary( key , binaryPlaintext.length() );


        for ( int i = 0; i < binaryPlaintext.length(); i++ )
        {
            if ( exclusiveOrKey.charAt(i) != binaryPlaintext.charAt(i) )
            {
                ciphertext += "1";
            }
            else
            {
                ciphertext += "0";
            }
        }
        return ciphertext;
    }


    public static long getOneTimePadUpperBound( String plaintext )
    {
        String binaryPlaintext = Ask2( plaintext );
        int digitsOfBinary = binaryPlaintext.length();
        return (long)( Math.pow( 2, digitsOfBinary ) ) - 1;
    }


    public static String OneTimePad_random( String plaintext )
    {
        String ciphertext = "";
        String binaryPlaintext = Ask2( plaintext );

        int digitsOfBinary = binaryPlaintext.length();
        long upperBoundOfDecimal = (long)( Math.pow( 2, digitsOfBinary ) ) - 1;
        String exclusiveOrKey = Crypto.decimalToBinary( (long) (Math.random() * ( upperBoundOfDecimal + 1)) , binaryPlaintext.length() );


        for ( int i = 0; i < binaryPlaintext.length(); i++ )
        {
            if ( exclusiveOrKey.charAt(i) != binaryPlaintext.charAt(i) )
            {
                ciphertext += "1";
            }
            else
            {
                ciphertext += "0";
            }
        }

        return ciphertext;
    }

}
