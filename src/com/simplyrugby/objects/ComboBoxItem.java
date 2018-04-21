package com.simplyrugby.objects;

/**
 * @author Euan
 */
public class ComboBoxItem {

    private String itemText = null;
    private int itemID = 0;

    public ComboBoxItem(String itemText, int itemID) {
        this.itemText = itemText;
        this.itemID = itemID;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    @Override
    public String toString() {
        return itemText;
    }
}
