package co.nz.cloudm.addons.intercom;

import com.vaadin.shared.JavaScriptExtensionState;

import java.util.HashMap;
import java.util.Map;

public class IntercomState extends JavaScriptExtensionState {

    public String appId;

    public Map<String, Object> userData = new HashMap<>();

}
