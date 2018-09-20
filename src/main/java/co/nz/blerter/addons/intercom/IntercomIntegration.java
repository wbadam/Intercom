package co.nz.blerter.addons.intercom;

import com.vaadin.annotations.JavaScript;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.shared.Registration;
import com.vaadin.ui.UI;

import java.util.Date;
import java.util.Optional;

@JavaScript("intercom.js")
public class IntercomIntegration extends AbstractJavaScriptExtension {

    private Registration viewChangeListenerHandle;

    public IntercomIntegration(String appId) {
        getState().appId = appId;
    }

    public void extend(UI target) {
        super.extend(target);

        Navigator navigator = target.getNavigator();
        if (navigator != null) {
            viewChangeListenerHandle = target.getNavigator().addViewChangeListener(new ViewChangeListener() {
                @Override
                public boolean beforeViewChange(ViewChangeEvent event) {
                    return true;
                }

                @Override
                public void afterViewChange(ViewChangeEvent event) {
                    update();
                }
            });
        }
    }

    @Override
    public void remove() {
        Optional.ofNullable(viewChangeListenerHandle).ifPresent(Registration::remove);
        super.remove();
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

    public void update() {
        callFunction("update");
    }

    public void setUserData(String attributeName, String data) {
        internalSetUserData(attributeName, data);
    }

    public void setUserData(String attributeName, int data) {
        internalSetUserData(attributeName, data);
    }

    public void setUserData(String attributeName, float data) {
        internalSetUserData(attributeName, data);
    }

    public void setUserData(String attributeName, boolean data) {
        internalSetUserData(attributeName, data);
    }

    public void setUserData(String attributeName, Date data) {
        internalSetUserData(attributeName, data);
    }

//    public void setUserData(String attributeName, List<?> data) {
//        internalSetUserData(attributeName, data);
//    }

    private void internalSetUserData(String attributeName, Object data) {
        getState().userData.put(attributeName, data);
    }

    public void removeUserData(String attributeName) {
        getState().userData.remove(attributeName);
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
