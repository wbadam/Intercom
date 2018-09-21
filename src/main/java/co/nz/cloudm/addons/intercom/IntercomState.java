package co.nz.cloudm.addons.intercom;

import com.vaadin.shared.JavaScriptExtensionState;

import java.util.HashMap;
import java.util.Map;

public class IntercomState extends JavaScriptExtensionState {

    /**
     * Stores the Intercom app id.
     */
    public String appId;

    /**
     * Stores all user data.
     */
    public Map<String, Object> userData = new HashMap<>();

}
