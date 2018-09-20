package co.nz.blerter.addons.intercom;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;

@JavaScript("intercom.js")
public class IntercomIntegration extends AbstractJavaScriptExtension {

    public IntercomIntegration(String appId) {
        getState().appId = appId;
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
