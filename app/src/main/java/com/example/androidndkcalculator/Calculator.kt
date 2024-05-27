package com.example.androidndkcalculator

class Calculator {

    external fun add(a: Int, b: Int): Int

    external fun multiply(a: Int, b: Int): Int

    external fun subtracation(a: Int, b: Int): Int

    external fun division(a: Int, b: Int): Int

    companion object {
        init {
            System.loadLibrary("androidndkcalculator")
        }
    }
}