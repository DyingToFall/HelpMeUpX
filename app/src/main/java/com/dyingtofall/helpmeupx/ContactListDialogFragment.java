package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by James W on 8/15/2017.
 */

public class ContactListDialogFragment extends DialogFragment {

    //ArrayList<String> cArrayList;
    //ArrayAdapter<String> cAdapter;


    ListView contactList;
    //LinearLayout linearLay;
    ArrayList<ContactListInfo> cLArrayList;
    ContactListAdapter cLAdapter;
    Cursor cursor;
    TextView textView;


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View clView = getActivity().getLayoutInflater().inflate(R.layout.contact_list_layout, null);
        builder.setView(clView);
        builder.setTitle("Contacts");


/*
        contactList = (ListView) clView.findViewById(R.id.listViewContactList);
        cAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.contact_list_row_layout, cArrayList);
        contactList.setAdapter(cAdapter);


        String phoneNumber = null;
        String email = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        StringBuffer output;

        ContentResolver contentResolver = getActivity().getContentResolver();

        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        // Iterate every contact in the phone
        if (cursor.getCount() > 0) {


            while (cursor.moveToNext()) {
                output = new StringBuffer();


                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    output.append("\n First Name:" + name);

                    //This is to read multiple phone numbers associated with the same contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:" + phoneNumber);

                    }

                    phoneCursor.close();

                    // Read every email id associated with the contact
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (emailCursor.moveToNext()) {

                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));

                        output.append("\n Email:" + email);

                    }

                    emailCursor.close();
                }

                // Add the contact to the ArrayList
                cArrayList.add(output.toString());
                //cLArrayList.add(0,output.toString());

                contactList.setAdapter(cAdapter);



            }
        }
        */

        cLArrayList = new ArrayList<ContactListInfo>();
        cLAdapter = new ContactListAdapter(getContext(), R.layout.list_item, cLArrayList);
        contactList = (ListView) clView.findViewById(R.id.listViewContactList);
        textView = (TextView) clView.findViewById(R.id.contactInfo);



        /*
        CheckBox checkBox;
        checkBox = (CheckBox) clView.findViewById(R.id.listCheckBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    //checked
                    contactList.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                }
                else
                {
                    //not checked
                    contactList.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }

            }
        });
        */

        String phoneNumber = null;
        String email = null;
        int counter = 0;
        boolean firstCount = true;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        StringBuffer output;

        ContentResolver contentResolver = getActivity().getContentResolver();

        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        // Iterate every contact in the phone
        if (cursor.getCount() > 0) {


            while (cursor.moveToNext()) {
                output = new StringBuffer();


                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                ContactListInfo tempContact = new ContactListInfo();


                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    tempContact.setContactName(name);

                    firstCount = true;

                    String numList[];
                    numList = new String[12];

                    //This is to read multiple phone numbers associated with the same contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    hasPhoneNumber = 0;

                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));


                        if (!firstCount) {
                            tempContact = new ContactListInfo();
                            tempContact.setContactName(name);
                            numList[hasPhoneNumber] = phoneNumber;
                            hasPhoneNumber = hasPhoneNumber + 1;
                           // cLArrayList.add(counter, tempContact);
                        } else {
                            firstCount = false;
                            numList[hasPhoneNumber] = phoneNumber;
                            hasPhoneNumber = hasPhoneNumber + 1;
                           // cLArrayList.add(counter, tempContact);
                        }

                    }

                    tempContact.setContactPhones(numList);
                    cLArrayList.add(counter, tempContact);

                    phoneCursor.close();


                }

                // Add the contact to the ArrayList
                //cArrayList.add(output.toString());
                //cLArrayList.add(0,output.toString());

                //contactList.setAdapter(cAdapter);


            }
            contactList.setAdapter(cLAdapter);
        }

        contactList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Mark this view row as selected.
                view.setSelected(true);
                view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                Toast.makeText(getContext(), "item clicked : \n" + contactList.getPositionForView(view), Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();

                String currContName = cLArrayList.get(i).getContactName();
                String currContInfo[];
                currContInfo = cLArrayList.get(i).getContactPhones();

                args.putInt("DialogPosition", i); //might not need this right now
                args.putString("PhoneName", currContName);
                args.putStringArray("PhoneNumbers", currContInfo);

                //args.putParcelableArrayList("cArrayList", cLArrayList);
                //args.putStringArrayList("cArraylist", cLArrayList);

                PhonePickerDialogFragment pLD = new PhonePickerDialogFragment();
                pLD.setArguments(args);
                pLD.show(getFragmentManager(),"Phone Picker Dialog");
            }
        });


        return builder.create();
    }




}
