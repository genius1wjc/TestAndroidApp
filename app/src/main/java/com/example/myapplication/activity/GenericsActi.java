package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsActi extends AppCompatActivity {

    @Override
    // @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generics);

        Box1 box1 = new Box1("Text 1");
        Object text1 = box1.getT();
        if (text1 instanceof CharSequence) {
            TextView tv1 = (TextView) findViewById(R.id.tv1);
            tv1.setText((CharSequence)text1);
        }

        Box2<String> box2 = new Box2<>();
        box2.set("Text 2");
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText(box2.toString());

        Box3<String, Integer> box3 = new Box3<>();
        box3.setK("Hello").setInteger(2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setText(box3.toString());

        Box3 rawBox = box3; // For backward compatibility, assigning a parameterized type to its raw type is allowed
        rawBox.setInteger(1).setK("text");

        String text4 = "Am I valid?";
        TextView tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setText("Text is " + isValidText(text4)); // Using type inference

        // The inference algorithm tries to find the most specific type that works with all of the arguments
        Serializable s = pick("d", new ArrayList<String>());
        if (s instanceof Serializable) {
            TextView tv5 = (TextView) findViewById(R.id.tv5);
            tv5.setText("Serializable");
        }

        Box4 box4 = new Box4(false);
        TextView tv6 = (TextView) findViewById(R.id.tv6);
        tv6.setText(box4.getMessage());

        Double[] doubles = {1.1, 2.2, 3.3};
        List<Double> list = Arrays.asList(doubles);
        double sum = sumOfList(list);
        TextView tv7 = (TextView) findViewById(R.id.tv7);
        tv7.setText("Sum of doubles is " + sum);

        List<Object> objects = new ArrayList<>();
        int sum2 = addNumbers(objects);
        TextView tv8 = (TextView) findViewById(R.id.tv8);
        tv8.setText("Size of objects is " + sum2);
    }

    // Generic class
    class Box1<T> {
        private T t;

        Box1(T t) {
            this.t = t;
        }

        T getT() {
            return t;
        }
    }

    // Generic class with upper bounded type parameter
    class Box2<T extends String> {
        private T t;

        void set(T t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return t;
        }
    }

    // Generic class with multiple upper bounded type parameter
    class Box3<K extends CharSequence, V extends Integer> {
        private K k;
        private Integer i;

        Box3 setK(K k) {
            this.k = k;
            return this;
        }

        Box3 setInteger(V v) {
            i = v;
            return this;
        }

        @Override
        public String toString() {
            if (k instanceof String)
                return "K is " + (String)k + " and integer is" + i;
            else
                return "Integer is" + i;
        }
    }

    // Generic method
    private static <T extends CharSequence> boolean isValidText(T t) {
        return t != null && !t.equals("");
    }

    static <T> T pick(T a1, T a2) {
        return a2;
    }

    class Box4<T> {
        String message = "";
        // Constructors can be generic (in other words, declare their own formal type parameters)
        // in both generic and non-generic classes
        <X extends Boolean> Box4(X x) {
            if (x != null)
                message = "Not null";
        }

        String getMessage() {
            return message;
        }
    }

    // Upper bounded wildcard
    public static double sumOfList(List<? extends Number> list) {
        double s = 0.0;
        for (Number n : list)
            s += n.doubleValue();
        return s;
    }

    // Lower bounded wildcard
    public static int addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        return list.size();
    }
}
