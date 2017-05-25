package com.example.themion.calculator;

/**
 * Created by Themion on 2017-05-24.
 */

public class Node {
    private double printData;
    private double calcData;
    private int calcOp;
    private int printOp;
    private Node prev;
    private Node next;

    Node()
    {
        this.printData = 0;
        this.calcData = 0;
        this.printOp = 0;
        this.calcOp = 0;
        this.prev = null;
        this.next = null;
    }

    Node(int calcOp)
    {
        this.printData = 0;
        this.calcData = 0;
        this.printOp = calcOp;
        this.calcOp = calcOp;
        this.prev = null;
        this.next = null;
    }

    Node(Node prev)
    {
        this.printData = 0;
        this.calcData = 0;
        this.printOp = 0;
        this.calcOp = 0;
        this.prev = prev;
        this.next = null;
    }

    Node(double calcData, int calcOp)
    {
        this.printData = calcData;
        this.calcData = calcData;
        this.printOp = calcOp;
        this.calcOp = calcOp;
        this.prev = null;
        this.next = null;
    }

    double getPrintData() {return this.printData;}
    double getCalcData() {return this.calcData;}
    int getPrintOp() {return this.printOp;}
    int getCalcOp() {return this.calcOp;}
    Node getPrev() {return this.prev;}
    Node getNext() {return this.next;}

    void setPrintData(double printData) {this.printData = printData; this.calcData = printData;}
    void setCalcData(double calcData) {this.calcData = calcData;}
    void setPrintOp(int printOp) {this.printOp = printOp;}
    void setCalcOp(int calcOp) {this.calcOp = calcOp; this.printOp = calcOp;}
    void setNext(Node next) {this.next = next;}
    void setPrev(Node prev) {this.prev = prev;}
/*
        node seek(int input)
        {
            if(input == 0) return this;
            else return this.next.seek(input - 1);
        }
        */
}
