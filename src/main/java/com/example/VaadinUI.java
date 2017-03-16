package com.example;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alejandro Duarte
 */
@SpringUI
public class VaadinUI extends UI {

    private Grid<Person> grid = new Grid<>(Person.class);
    private TextField textField = new TextField(event -> updateGrid());

    private final PersonService service;

    public VaadinUI(PersonService service) {
        this.service = service;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        textField.setPlaceholder("Search");

        VerticalLayout layout = new VerticalLayout(textField);
        layout.addComponentsAndExpand(grid);
        setContent(layout);

        updateGrid();
    }

    private void updateGrid() {
        grid.setDataProvider(
                (sortOrders, offset, limit) -> {
                    Map<String, Boolean> sortOrder = sortOrders.stream()
                            .collect(Collectors.toMap(
                                    sort -> sort.getSorted(),
                                    sort -> sort.getDirection() == SortDirection.ASCENDING));

                    return service.searchByFirstNameOrLastName(textField.getValue(), textField.getValue(), offset, limit, sortOrder).stream();
                },
                () -> service.searchByFirstNameOrLastName(textField.getValue(), textField.getValue())
        );
    }

}
