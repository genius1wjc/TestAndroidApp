package com.example.myapplication;

/**
 * Created by jiechao on 6/25/16. This is just a class to test Gson's ability
 */
public class Student {

    private String name;
    private int id;
    private boolean isMale;

    public enum Grade { ONE, TWO, THREE }
    private Grade grade;

    /**
     * Transient field shouldn't be in json
     */
    private transient String ssn = "123456";

    private int[] numbers = null;

    boolean[] arr = { false, true, false };

    public Student(String name, int id, boolean isMale, Grade grade) {
        this.name = name;
        this.id = id;
        this.isMale = isMale;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student with name " + name + ", id " + id  + ", is male: " + isMale;
    }

    /**
     * Fields in inner class shouldn't be included in json
     */
    public class Inner {
        int field1 = 0;
        boolean field2 = false;

        public String manipulate(String text) {
            return text;
        }
    }

    public static class Nested {
        public float field3 = 2.3f;
    }
}
