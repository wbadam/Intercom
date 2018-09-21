package co.nz.cloudm.addons.intercom;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.addonhelpers.AbstractTest;

public class IntercomComponentUsageUI extends AbstractTest {

    private static String APP_ID = "APP_ID";

    @Override
    public Component getTestComponent() {
        VerticalLayout layout = new VerticalLayout();

        IntercomIntegration intercom = new IntercomIntegration(APP_ID);
        intercom.extend(this);

        Label label = new Label("This page should display Intercom icon on the bottom right corner.");
        layout.addComponents(label);

        TextField idField = new TextField("App id");
        Button bootButton = new Button("Boot");
        bootButton.addClickListener(event -> {
            intercom.boot(idField.getValue());
        });
        layout.addComponents(idField, bootButton);

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.addClickListener(clk -> {
            intercom.shutdown();
        });
        layout.addComponent(shutdownButton);

        return layout;
    }
}
