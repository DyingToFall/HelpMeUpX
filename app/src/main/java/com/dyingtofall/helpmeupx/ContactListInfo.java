package com.dyingtofall.helpmeupx;

/**
 * Created by James W on 8/16/2017.
 */

public class ContactListInfo {
    String contactName;
    String contactPhone;
    String contactEmail; //might get rid of this later
    boolean contactBox;

    public ContactListInfo()
    {
        this.contactName = "";
        this.contactPhone = "";
        this.contactEmail = "";
        this.contactBox = false;
    }

    public String getContactName()
    {
        return contactName;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public boolean getContactBox()
    {
        return contactBox;
    }

    public void setContactName(String cN) {contactName = cN; }

    public void setContactPhone(String cP) {contactPhone = cP; }

    public void setContactEmail(String cE) {contactEmail = cE; }

    public void setContactBox (boolean cB) {contactBox = cB; }


}
