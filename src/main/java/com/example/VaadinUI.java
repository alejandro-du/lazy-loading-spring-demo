package com.example;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alejandro Duarte
 */
@SpringUI
public class VaadinUI extends UI {

    private final PersonService service;

    public VaadinUI(PersonService service) {
        this.service = service;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setSizeFull();

        grid.setDataProvider(
                (sortOrders, offset, limit) -> {
                    Map<String, Boolean> sortOrder = sortOrders.stream()
                            .collect(Collectors.toMap(
                                    sort -> sort.getSorted(),
                                    sort -> sort.getDirection() == SortDirection.ASCENDING));

                    return service.findAll(offset, limit, sortOrder).stream();
                },
                () -> service.count()
        );

        VerticalLayout layout = new VerticalLayout(grid);
        layout.setSizeFull();
        setContent(layout);
    }

}
