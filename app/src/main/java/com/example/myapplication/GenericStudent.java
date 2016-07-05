package com.example.myapplication;

/**
 * Created by jiechao on 6/25/16.
 */
public class GenericStudent<T> {
    T value;
    String name = "Da Yun";

    public GenericStudent(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Generic student with value" + value;
    }
}
