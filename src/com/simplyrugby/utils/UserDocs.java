package com.simplyrugby.utils;

import java.awt.*;
import java.net.URL;

/**
 * UserDocs utility class
 *
 * @author Euan
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
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            URL userDocsLocation = new URL("https://user.docs.gradedunit.euanriggans.com");
            desktop.browse(userDocsLocation.toURI());
        } catch (Exception e) {
            SimpleAlerts.exceptionAlert("Unexpected error occured when attempting to open the userdocs", e);
        }
    }

}
