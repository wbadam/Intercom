package co.nz.blerter.addons.intercom;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;

@JavaScript("intercom.js")
public class IntercomIntegration extends AbstractJavaScriptExtension {

    public IntercomIntegration(String appId) {
        getState().appId = appId;
    }

    public void setAppId(String appId) {
        getState().appId = appId;
    }

    public String getAppId() {
        return getState(false).appId;
    }

    public void boot(String appId) {
        setAppId(appId);
        callFunction("boot");
    }

    public void shutdown() {
        callFunction("shutdown");
    }

    @Override
    protected IntercomState getState() {
        return (IntercomState) super.getState();
    }

    @Override
    protected IntercomState getState(boolean markAsDirty) {
        return (IntercomState) super.getState(markAsDirty);
    }
}
