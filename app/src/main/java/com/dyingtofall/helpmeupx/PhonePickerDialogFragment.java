package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by James W on 8/29/2017.
 */

public class PhonePickerDialogFragment extends DialogFragment {

    ListView pContactList;
    ArrayList<String> pLArrayList;
    PhonePickAdapter pLAdapter;
    boolean boolList[];
    TextView textView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String currentName;
    int currentInt;
    Bundle mArgs;



    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View plView = getActivity().getLayoutInflater().inflate(R.layout.emergency_list_layout, null);
        builder.setView(plView);
        builder.setTitle("Choose Phone Numbers");


        pLArrayList = new ArrayList<String>();
        pLAdapter = new PhonePickAdapter(getContext(), R.layout.list_item, pLArrayList);
        pContactList = (ListView) plView.findViewById(R.id.listViewEmergencyList);
        View pV = plView.findViewById(R.id.elTextView);



        mArgs = getArguments();
        currentInt = mArgs.getInt("DialogPosition");
        currentName = mArgs.getString("PhoneName");

        ((TextView)pV).append(currentName + ".\n \n");

        ArrayList<String> extractedPhones = mArgs.getStringArrayList("PhoneNumbers");
        ArrayList<String> tempList = new ArrayList<String>();
        HashSet<String> tempHash = new HashSet<String>();
        ArrayList<String> finalList = new ArrayList<String>();



        if (extractedPhones.size() > 1)
        {
            for (int i = extractedPhones.size() - 1; i >= 0; i--)
            {
                String tempString = extractedPhones.get(i).replaceAll("[^0-9]+","");
                if ((tempString.charAt(0) == 1)&&(tempString.length() > 7))
                {
                    String tempString2 = tempString.replaceFirst("1","");
                    tempList.add(tempString2);
                   // pLArrayList.add(tempString2);
                }
                else
                {
                    tempList.add(tempString);
                    //pLArrayList.add(tempString);
                }

                //pLArrayList.remove(0)
            }
            tempHash.addAll(tempList);
            tempList.clear();
            tempList.addAll(tempHash);


                    for (int j = 0; j < tempList.size(); j++)
                    {
                        String aTempString = PhoneNumberUtils.formatNumber(tempList.get(j));
                        finalList.add(aTempString);
                    }


            pLArrayList.addAll(finalList);

        }
        else
        {
            String tempString = extractedPhones.get(0).replaceAll("[^0-9]+","");
            String finalString;
            if ((tempString.charAt(0) == 1)&&(tempString.length() > 7))
            {
                String tempString2 = tempString.replaceFirst("1","");
                finalString = PhoneNumberUtils.formatNumber(tempString2);
                pLArrayList.add(finalString);
            }
            else
            {
                finalString = PhoneNumberUtils.formatNumber(tempString);
                pLArrayList.add(finalString);
            }
        }



            boolList = new boolean[pLArrayList.size()];
            sharedPreferences = getContext().getSharedPreferences("prefs",Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();

            pContactList.setAdapter(pLAdapter);






        pContactList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        pContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if ((boolList[pContactList.getPositionForView(view)] == true)||
                        ((boolList[pContactList.getPositionForView(view)] == false)&&(sharedPreferences.contains(pLArrayList.get(i)))))
                {
                    view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    Toast.makeText(getContext(), "The number " + pLArrayList.get(pContactList.getPositionForView(view))
                            + " will be removed from the Emergency Contact List.", Toast.LENGTH_SHORT).show();
                    boolList[pContactList.getPositionForView(view)] = false;
                }
                else
                {
                    view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    Toast.makeText(getContext(), "The number " + pLArrayList.get(pContactList.getPositionForView(view))
                            + " will be added to the Emergency Contact List.", Toast.LENGTH_SHORT).show();
                    boolList[pContactList.getPositionForView(view)] = true;
                }

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                int tempNum = 0;
                if  (sharedPreferences.contains("EmergencySize"))
                {
                    tempNum = sharedPreferences.getInt("EmergencySize",0);
                }
                boolean highlight = false;
                for(int z = 0; z < boolList.length; z++)
                {
                    if (boolList[z]== true)
                    {
                        editor.putString(pLArrayList.get(z), pLArrayList.get(z));
                        editor.putString("EmNum" + Integer.toString(tempNum),pLArrayList.get(z));
                        editor.putString("EmName" + Integer.toString(tempNum), currentName);
                        Toast.makeText(getContext(), "EmNum" + Integer.toString(tempNum), Toast.LENGTH_SHORT).show();
                        if (tempNum == 0)
                        {
                            editor.putInt("EmergencySize", 1);
                            tempNum = tempNum + 1;
                        }
                        else
                        {
                            tempNum = tempNum + 1;
                        }
                        highlight = true;

                    }
                    else
                    {

                        if (sharedPreferences.contains(pLArrayList.get(z)))
                        {
                            boolean removeBool = false;
                            for (int y = 0; y < tempNum-1; y++)
                            {
                                String tempSString = sharedPreferences.getString("EmNum" + Integer.toString(y),"");
                                String tempS2String = sharedPreferences.getString("EmNum" + Integer.toString(y+1),"");
                                String tempS3String = sharedPreferences.getString("EmName" + Integer.toString(y+1),"");
                                if (tempSString.equals(pLArrayList.get(z)))
                                {
                                    editor.remove("EmNum" + Integer.toString(y));
                                    editor.remove("EmName"+ Integer.toString(y));
                                    removeBool = true;
                                }
                                if (removeBool == true)
                                {
                                    editor.putString("EmNum" + Integer.toString(y),tempS2String);
                                    editor.putString("EmName" + Integer.toString(y),tempS3String);
                                }
                            }



                            editor.remove(pLArrayList.get(z));
                            editor.remove("EmNum" + Integer.toString(tempNum-1));
                            editor.remove("EmName" + Integer.toString(tempNum-1));
                            tempNum = tempNum - 1;
                        }
                    }
                }
                if (highlight == true)
                {
                    editor.putString(currentName, currentName);
                   // mArgs.putBoolean("green", true);
                    //mArgs.putString("conName", currentName);
                }
                else
                {
                    if (sharedPreferences.contains(currentName))
                    {
                        editor.remove(currentName);
                    }
                    //mArgs.putBoolean("green", false);
                }
                if (sharedPreferences.contains("EmergencySize"))
                {
                    editor.putInt("EmergencySize", tempNum);
                }
                editor.commit();
                if (highlight == true)
                {
                    Toast.makeText(getContext(), "Adding selected numbers to Emergency Contact List.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "No numbers from " + currentName + " will be used.", Toast.LENGTH_SHORT).show();
                }


               // ContactListDialogFragment cLDFrag = new ContactListDialogFragment();
                dialogInterface.dismiss();
               // cLDFrag.show(getFragmentManager(),"Contact List Dialog");

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
                //ContactListDialogFragment cLDFrag = new ContactListDialogFragment();
                //cLDFrag.show(getFragmentManager(),"Contact List Dialog");
            }
        });

        return builder.create();
    }




}
