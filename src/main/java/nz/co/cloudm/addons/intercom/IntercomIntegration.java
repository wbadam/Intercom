package nz.co.cloudm.addons.intercom;

import com.vaadin.annotations.JavaScript;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.shared.Registration;
import com.vaadin.ui.UI;

import java.util.Date;
import java.util.Optional;

/**
 * Extension for integrating Intercom messaging in a UI.
 *
 * @see <a href="https://www.intercom.com">Intercom</a>
 */
@JavaScript("intercom.js")
public class IntercomIntegration extends AbstractJavaScriptExtension {

    private static final String ATTR_EMAIL = "email";
    private static final String ATTR_USER_ID = "user_id";

    private Registration viewChangeListenerHandle;

    /**
     * Creates an Intercom integration extension with an app id.
     * @param appId the app id given by Intercom
     */
    public IntercomIntegration(String appId) {
        setAppId(appId);
    }

    /**
     * Creates an Intercom integration extension with an app id.
     * @param appId the app id given by Intercom
     * @param userEmail email address of the logged in user
     * @param userId id of the logged in user
     */
    public IntercomIntegration(String appId, String userEmail, String userId) {
        this(appId);
        setUserEmail(userEmail);
        setUserId(userId);
    }

    /**
     * Extends a UI with the Intercom integration.
     * @param target the UI to be extended
     */
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

    /**
     * Sets the Intercom app id.
     * @param appId app id given by Intercom
     */
    public void setAppId(String appId) {
        getState().appId = appId;
    }

    /**
     * Gets the Intercom app id.
     * @return app id set for this extension
     */
    public String getAppId() {
        return getState(false).appId;
    }

    /**
     * Sets the email address for the logged in user.
     * @param email email address of the logged in user
     */
    public void setUserEmail(String email) {
        setUserData(ATTR_EMAIL, email);
    }

    /**
     * Gets the email address of the logged in user.
     * @return the email address set for this extension
     */
    public String getUserEmail() {
        return getUserData(ATTR_USER_ID).toString();
    }

    /**
     * Sets the user id for the logged in user.
     * @param userId id of the logged in user
     */
    public void setUserId(String userId) {
        setUserData(ATTR_USER_ID, userId);
    }

    /**
     * Gets the user id of the logged in user.
     * @return the user id set for this extension
     */
    public String getUserId() {
        return getUserData(ATTR_USER_ID).toString();
    }

    /**
     * Fires the Intercom {@code boot} command with the given app id. Also sends all previous settings.
     * @param appId the app id with which to boot Intercom
     */
    public void boot(String appId) {
        setAppId(appId);
        callFunction("boot");
    }

    /**
     * Fires the Intercom {@code shutdown} command.
     */
    public void shutdown() {
        callFunction("shutdown");
    }

    /**
     * Fires the Intercom {@code update} command. Also sends all previous settings.
     */
    public void update() {
        callFunction("update");
    }

    /**
     * Sets an arbitrary user data.
     * @param attributeName attribute name
     * @param data string data
     */
    public void setUserData(String attributeName, String data) {
        internalSetUserData(attributeName, data);
    }

    /**
     * Sets an arbitrary user data.
     * @param attributeName attribute name
     * @param data integer data
     */
    public void setUserData(String attributeName, int data) {
        internalSetUserData(attributeName, data);
    }

    /**
     * Sets an arbitrary user data.
     * @param attributeName attribute name
     * @param data float data
     */
    public void setUserData(String attributeName, float data) {
        internalSetUserData(attributeName, data);
    }

    /**
     * Sets an arbitrary user data.
     * @param attributeName attribute name
     * @param data boolean data
     */
    public void setUserData(String attributeName, boolean data) {
        internalSetUserData(attributeName, data);
    }

    /**
     * Sets an arbitrary user data.
     * @param attributeName attribute name
     * @param data date data
     */
    public void setUserData(String attributeName, Date data) {
        internalSetUserData(attributeName, data);
    }

//    public void setUserData(String attributeName, List<?> data) {
//        internalSetUserData(attributeName, data);
//    }

    private void internalSetUserData(String attributeName, Object data) {
        getState().userData.put(attributeName, data);
    }

    /**
     * Gets an arbitrary user data.
     * @param attributeName attribute name
     * @return the user data associated with the attribute name
     */
    public Optional<Object> getUserData(String attributeName) {
        return getState().userData.containsKey(attributeName)
                ? Optional.ofNullable(getState().userData.get(attributeName))
                : Optional.empty();
    }

    /**
     * Removes the user data registered with the given attribute name.
     * @param attributeName attribute name
     */
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
