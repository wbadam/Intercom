package nz.co.cloudm.addons.intercom;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.addonhelpers.AbstractTest;

public class IntercomComponentUsageUI extends AbstractTest {

    /*** Replace this with a valid app id ***/
    private static String APP_ID = "APP_ID";

    @Override
    public Component getTestComponent() {
        VerticalLayout layout = new VerticalLayout();

        IntercomIntegration intercom = new IntercomIntegration(APP_ID);
        intercom.extend(this);

        Label hint = new Label("This page should display Intercom icon on the bottom right corner after rebooted with app id. " +
                "If visitors are not supported in Intercom, update user email and id.");
        layout.addComponents(hint);

        TextField appId = new TextField("App id");
        Button boot = new Button("Boot", e -> {
            intercom.boot(appId.getValue());
        });
        layout.addComponents(new HorizontalLayout(appId, boot));

        TextField userEmail = new TextField("User email");
        TextField userId = new TextField("User ID");
        Button update = new Button("Update", e -> {
            intercom.setUserEmail(userEmail.getValue());
            intercom.setUserId(userId.getValue());
            intercom.update();
        });
        layout.addComponent(new HorizontalLayout(userEmail, userId, update));

        Button shutdown = new Button("Shutdown");
        shutdown.addClickListener(clk -> {
            intercom.shutdown();
        });
        layout.addComponent(shutdown);

        return layout;
    }
}
