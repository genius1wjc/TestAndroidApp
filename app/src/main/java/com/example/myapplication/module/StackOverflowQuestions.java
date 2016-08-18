package com.example.myapplication.module;

import java.util.List;

/**
 * Created by jiechao on 8/18/16.
 */
public class StackOverflowQuestions {

    public List<Question> items;

    public class Question {
        String title;
        String link;

        @Override
        public String toString() {
            return title;
        }
    }
}
