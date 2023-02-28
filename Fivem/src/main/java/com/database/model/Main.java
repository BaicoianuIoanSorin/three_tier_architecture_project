package com.database.model;

import java.util.Objects;

public class Main {
    private String id;
    private String name;
    private String args;

    public Main() {

    }
    public Main(String id, String name, String args) {
        this.id = id;
        this.name = name;
        this.args = args;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Main main = (Main) o;
        return Objects.equals(id, main.id) && Objects.equals(name, main.name) && Objects.equals(args, main.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, args);
    }
}
