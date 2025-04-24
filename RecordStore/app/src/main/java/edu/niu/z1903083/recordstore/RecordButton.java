package edu.niu.z1903083.recordstore;

import android.content.Context;
import android.widget.Button;

public class RecordButton
        extends androidx.appcompat.widget.AppCompatButton
{
    private Record record;

    public RecordButton(Context context, Record newRecord)
    {
        super(context);
        record = newRecord;
    }

    public double getPrice()
    {
        return record.getPrice();
    }
}