package com.kerchin.demo.providerandbroadcast.ContentProvider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kerchin.demo.providerandbroadcast.R;

/**
 * Created by Kerchin on 2016/7/13 0013.
 */
public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }

    private void addContacts(View view) {
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        Uri uri = cr.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        Long raw_contact_id = ContentUris.parseId(uri);
        values.clear();
        //插入人名
        values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID, raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "#张三");
        values.put(ContactsContract.CommonDataKinds.StructuredName.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        uri = cr.insert(ContactsContract.Data.CONTENT_URI, values);
        //插入电话
        values.clear();
        values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "13333333333");
        values.put(ContactsContract.CommonDataKinds.Phone.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        uri = cr.insert(ContactsContract.Data.CONTENT_URI, values);
    }

    private void queryContacts(View view) {
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(ContactsContract.Contacts.CONTENT_URI
                , new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME}
                , null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("_id"));
                Log.d("queryinfo", "id:" + id);
                Log.d("queryinfo", "name:" + c.getString(c.getColumnIndex("display_name")));
                Cursor c1 = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE
                }, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                if (c1 != null) {
                    while (c1.moveToNext()) {
                        int type = c1.getInt(c1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        if (type == ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                            Log.d("queryinfo", "home" + c1.getString(c1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        else if (type == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                            Log.d("queryinfo", "mobile" + c1.getString(c1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    }
                    c1.close();
                }
                Cursor c2 = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, new String[]{
                        ContactsContract.CommonDataKinds.Email.DATA, ContactsContract.CommonDataKinds.Email.TYPE
                }, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + id, null, null);
                if (c2 != null) {
                    while (c2.moveToNext()) {
                        int type = c2.getInt(c2.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        if (type == ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                            Log.d("queryinfo", "home" + c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                        else if (type == ContactsContract.CommonDataKinds.Email.TYPE_HOME)
                            Log.d("queryinfo", "home" + c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                    }
                    c2.close();
                }
            }
            c.close();
        }
    }
}
