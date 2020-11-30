package ru.otus.hw06.money;

import java.util.HashMap;
import java.util.Map;

public class Memento {
    private final Map<Notes, Integer> state = new HashMap<>();

    public Memento(Map<Notes, Integer> state) {
        this.state.putAll(state);
    }

    public Map<Notes, Integer> getState() {
        return state;
    }
}
