package nz.co.cloudm.addons.intercom;

import com.vaadin.shared.JavaScriptExtensionState;

import java.util.HashMap;
import java.util.Map;

public class IntercomState extends JavaScriptExtensionState {

    /**
     * Stores the Intercom app id.
     */
    public String appId;

    /**
     * Logged in user id.
     */
    public String userId;

    /**
     * Logged in user email.
     */
    public String userEmail;

    /**
     * Hash code generated for the logged in user.
     */
    public String userHash;

    /**
     * Stores all user data.
     */
    public Map<String, Object> userData = new HashMap<>();

}
