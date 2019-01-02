package jiangtongwang.cryptogram;

// original source file: kahermans.phonebookdemo.ContactAdapter.java
// edited and changed according to Cryptogram

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CryptoDataAdapter extends ArrayAdapter<CryptoData>
{
    private Context mContext;
    private List<CryptoData> cryptoDataList = new ArrayList<CryptoData>();

    public CryptoDataAdapter(Context context, ArrayList<CryptoData> list)
    {
        super(context, 0, list);
        mContext = context;
        cryptoDataList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;

        // Associates the list with the XML Layout file "crypto_data_view"
        if (listItem == null)
        {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.crypto_data_view, parent, false);
        }

        // Individually handles each CryptoData in the cryptoDataList
        CryptoData currentCryptoData = cryptoDataList.get(position);

        // Extracts the name of the CryptoData
        TextView name = (TextView) listItem.findViewById(R.id.textView_Name);
        name.setText(currentCryptoData.getName());

        // Extracts the description of the CryptoData
        TextView description = (TextView) listItem.findViewById(R.id.textView_Description);
        description.setText(currentCryptoData.getDescription());

        return listItem;
    }
}
