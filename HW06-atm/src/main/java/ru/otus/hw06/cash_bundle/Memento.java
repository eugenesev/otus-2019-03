package ru.otus.hw06.cash_bundle;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private final List<Integer> state = new ArrayList<>();

    public Memento(List<Integer> state) {
        this.state.addAll(state);
    }

    public List<Integer> getState() {
        return state;
    }
}
