package co.nz.blerter.addons.intercom;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.addonhelpers.AbstractTest;

public class IntercomComponentUsageUI extends AbstractTest {

    private static String APP_ID = "APP_ID";

    @Override
    public Component getTestComponent() {
        VerticalLayout layout = new VerticalLayout();

        IntercomIntegration intercom = new IntercomIntegration(APP_ID);
        addExtension(intercom);

        Label label = new Label("This page should display Intercom icon on the bottom right corner.");

        layout.addComponents(label);

        return layout;
    }
}
