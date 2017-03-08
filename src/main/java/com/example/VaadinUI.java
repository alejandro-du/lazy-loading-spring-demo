package com.example;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alejandro Duarte
 */
@SpringUI
public class VaadinUI extends UI {

    private final PersonService service;

    @Autowired
    public VaadinUI(PersonService service) {
        this.service = service;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setSizeFull();

        grid.setDataProvider(
                (sortOrder, offset, limit) -> service.findAll(offset, limit).stream(),
                () -> service.count()
        );

        VerticalLayout layout = new VerticalLayout(grid);
        layout.setSizeFull();
        setContent(layout);
    }

}
