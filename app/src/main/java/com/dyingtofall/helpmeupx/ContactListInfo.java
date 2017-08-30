package com.dyingtofall.helpmeupx;

/**
 * Created by James W on 8/16/2017.
 */

public class ContactListInfo {

    String contactName;
    String contactPhone;
    //String phoneTwo, phoneThree, phoneFour, phoneFive, phoneSix, phoneSeven, phoneEight;
    String contactPhones[];

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

    public String[] getContactPhones()
    {
        String cP[] = new String[12];
        int i = 0;
        while ((contactPhones[i] != null)&&(i < 12))
        {
            cP[i] = contactPhones[i];
            i = i + 1;
        }

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

    public void setContactPhones(String[] cPs) {contactPhones = cPs;}

}
