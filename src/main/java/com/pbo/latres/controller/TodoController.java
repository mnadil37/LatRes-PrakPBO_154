package com.pbo.latres.controller;

import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.model.TodoTask;
import com.pbo.latres.service.TodoService;
import com.pbo.latres.view.TodoView;

public class TodoController {

    private final TodoView view;

    private final TodoService service;

    public TodoController(
        TodoView view,
        TodoService service
    ) {
        this.view = view;
        this.service = service;

        init();
    }

    private void init() {

        refreshTable();

        view.onAdd(e -> addTodo());

        view.onUpdate(e -> updateTodo());

        view.onDelete(e -> deleteTodo());

        view.onClear(e -> clearForm());

        view.onTableSelect(e -> selectTodo());
    }

    private void refreshTable() {

        view.showTodos(
            service.getAllTodos()
        );
    }

    private void addTodo() {

        String title =
            view.getTitleInput();

        String status =
            view.getStatusInput();

        if (title.isEmpty()) {

            view.showMessage(
                "Task tidak boleh kosong"
            );

            return;
        }

        service.addTodo(
            new InsertTodoDTO(
                title,
                status
            )
        );

        refreshTable();

        view.clearForm();
    }

    private void updateTodo() {

        int selectedId =
            view.getSelectedTodoId();

        if (selectedId == -1) {

            view.showMessage(
                "Pilih data terlebih dahulu"
            );

            return;
        }

        TodoTask task = new TodoTask(
            selectedId,
            view.getTitleInput(),
            view.getStatusInput()
        );

        service.updateTodo(task);

        refreshTable();

        view.clearForm();
    }

    private void deleteTodo() {

        int selectedId =
            view.getSelectedTodoId();

        if (selectedId == -1) {

            view.showMessage(
                "Pilih data terlebih dahulu"
            );

            return;
        }

        service.deleteTodo(selectedId);

        refreshTable();

        view.clearForm();
    }

    private void clearForm() {
        view.clearForm();
    }

    private void selectTodo() {

        int selectedId =
            view.getSelectedTodoId();

        if (selectedId == -1) {
            return;
        }

        TodoTask task =
            service.getTodoById(selectedId);

        if (task != null) {
            view.setForm(task);
        }
    }
}