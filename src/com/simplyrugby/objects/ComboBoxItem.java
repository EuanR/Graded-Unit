package com.simplyrugby.objects;

/**
 * @author Euan
 */
public class ComboBoxItem {

    /**
     * The text which will be displayed in the combo box
     */
    private String itemText;
    /**
     * The 'hidden' value. Tends to be the players ID
     */
    private int playerID;

    /**
     * Constructor
     *
     * @param itemText The text to be displayed in the combo box
     * @param itemID   The hidden id
     */
    public ComboBoxItem(String itemText, int itemID) {
        this.itemText = itemText;
        this.playerID = itemID;
    }

    /**
     * Item text getter
     *
     * @return Returns the current item text
     */
    public String getItemText() {
        return itemText;
    }

    /**
     * Player ID getter
     *
     * @return Returns the current player ID
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Used by combo box to display text to the user
     * @return Returns the item text
     */
    @Override
    public String toString() {
        return itemText;
    }
}
