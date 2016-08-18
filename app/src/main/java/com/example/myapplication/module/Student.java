package com.example.myapplication.module;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jiechao on 6/25/16. This is just a class to test Gson's ability
 */
public class Student implements Parcelable {

    private String name;
    private int id;
    private boolean isMale;

    public enum Grade { ONE, TWO, THREE }
    private Grade grade;

    // We can also include child Parcelable objects.

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

    // This is where you write the values you want to save to the 'Parcel'.
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(id);
        // Do not have a method to write boolean for unknown reason
        out.writeValue(isMale);
        // Again, no method to write enum
        out.writeString(grade.name());
    }

    private Student(Parcel in) {
        name = in.readString();
        id = in.readInt();
        isMale = (boolean) in.readValue(null);
        grade = Grade.valueOf(in.readString());
    }

    // In the vast majority of cases you can simply return 0 for this.
    // There are cases where you need to use the constant `CONTENTS_FILE_DESCRIPTOR`
    // See http://stackoverflow.com/questions/4076946/parcelable-where-when-is-describecontents-used/4914799#4914799
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

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
