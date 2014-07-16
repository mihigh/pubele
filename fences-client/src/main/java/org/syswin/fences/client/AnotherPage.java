package org.syswin.fences.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class AnotherPage extends FlowPanel  {

    private TextBox parametersLbl;

    public AnotherPage() {
        add(this.parametersLbl = new TextBox());
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
