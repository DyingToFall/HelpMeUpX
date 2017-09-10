package com.dyingtofall.helpmeupx;

import java.util.ArrayList;

/**
 * Created by James W on 8/16/2017.
 */

public class ContactListInfo {

    String contactName;
    String contactPhone;
    //String phoneTwo, phoneThree, phoneFour, phoneFive, phoneSix, phoneSeven, phoneEight;
    ArrayList<String> contactPhones;

    ContactListInfo(){
       this.contactName = "";
        this.contactPhone = "";

}

    public String getContactName()
    {
        return contactName;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }

    public ArrayList<String> getContactPhones()
    {
        ArrayList<String> cP = new ArrayList<String>();
        cP = contactPhones;


        return cP;
    }

    /*
    public String getPhoneTwo() { return phoneTwo; }

    public String getPhoneThree() { return phoneThree; }

    public String getPhoneFour() { return phoneFour; }

    public String getPhoneFive() { return phoneFive; }

    public String getPhoneSix() { return phoneSix; }

    public String getPhoneSeven() { return phoneSeven; }

    public String getPhoneEight() { return phoneEight; }
    */

    public void setContactName(String cN) {contactName = cN; }

    public void setContactPhone(String cP) {contactPhone = cP; }

    /*

    public void setPhoneTwo(String pT) { phoneTwo = pT; }

    public void setPhoneThree(String pTh) { phoneThree = pTh; }

    public void setPhoneFour(String pF) { phoneFour = pF; }

    public void setPhoneFive(String pFi) { phoneFive = pFi; }

    public void setPhoneSix(String pS) { phoneSix = pS; }

    public void setPhoneSeven(String pSe) { phoneSeven = pSe; }

    public void setPhoneEight(String pE) { phoneEight = pE; }
    */

    public void setContactPhones(ArrayList<String> cPs) {contactPhones = cPs;}

}
