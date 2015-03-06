package com.igladkiy.gwt.ita.client;

import java.util.List;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.igladkiy.gwt.ita.client.api.client.HelloClient;
import com.igladkiy.gwt.ita.shared.domain.Hello;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class itaEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Defaults.setServiceRoot(GWT.getHostPageBaseURL());

		final HelloClient client = GWT.create(HelloClient.class);

		final VerticalPanel vp = new VerticalPanel();
		RootLayoutPanel.get().add(vp);

		Button b = new Button("Call ws");
		vp.add(b);
		b.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				client.getHellos(new MethodCallback<List<Hello>>() {

					public void onSuccess(Method method, List<Hello> response) {
						VerticalPanel panel = new VerticalPanel();
						for (Hello hello : response) {
							Label label = new Label(hello.getName());
							panel.add(label);
						}
						vp.add(panel);
					}

					public void onFailure(Method method, Throwable exception) {
						Label label = new Label("Error");
						vp.add(label);
					}
				});
			}
		});
	}

}
