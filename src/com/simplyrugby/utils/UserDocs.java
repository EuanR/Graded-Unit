package com.simplyrugby.utils;

import java.awt.*;
import java.net.URL;

/**
 * UserDocs utility class
 *
 * @author Euan Riggans
 */
public final class UserDocs {

    /**
     * Opens the user docs in the users default browser
     * <pre>
     *     {@code
     *      UserDocs.openUserDocs();
     *     }
     * </pre>
     */
    public static void openUserDocs() {
        try {
            if(Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                URL userDocsLocation = new URL("https://user.docs.gradedunit.euanriggans.com");
                desktop.browse(userDocsLocation.toURI());
            }
        } catch (Exception e) {
            SimpleAlerts.exceptionAlert("Unexpected error occured when attempting to open the userdocs", e);
        }
    }

}
