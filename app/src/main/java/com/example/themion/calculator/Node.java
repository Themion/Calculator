package com.example.themion.calculator;

/**
 * Created by Themion on 2017-05-24.
 */

public class Node {
    final int NOT_AN_OPERATOR = -1;

    final int noBrac = 0;
    final int isBrac = 1;

    final int noCalc = 0;
    final int squCalc = 1;
    final int powCalc = 2;

    final int noFunc = 0;
    final int sinFunc = 1;
    final int cosFunc = 2;
    final int tanFunc = 3;

    private double printData;
    private double calcData;

    private int calcOp;
    private int printOp;

    private int lBrac, rBrac;
    private int calc;
    private int func;

    private Node prev;
    private Node next;

    Node()
    {
        this.printData = 0;
        this.calcData = 0;
        this.printOp = NOT_AN_OPERATOR;
        this.calcOp = NOT_AN_OPERATOR;
        this.lBrac = noBrac;
        this.rBrac = noBrac;
        this.func = noFunc;
        this.prev = null;
        this.next = null;
    }

    double getPrintData() {return this.printData;}
    double getCalcData() {return this.calcData;}
    int getPrintOp() {return this.printOp;}
    int getCalcOp() {return this.calcOp;}
    int getLBrac() {return this.lBrac;}
    int getRBrac() {return this.rBrac;}
    int getFunc() {return this.func;}
    Node getPrev() {return this.prev;}
    Node getNext() {return this.next;}

    void setPrintData(double printData) {this.printData = printData; this.calcData = printData;}
    void setCalcData(double calcData) {this.calcData = calcData;}
    void setPrintOp(int printOp) {this.printOp = printOp; this.calcOp = printOp;}
    void setCalcOp(int calcOp) {this.calcOp = calcOp;}
    void setRBrac(int rBrac) {this.rBrac = rBrac;}
    void setLBrac(int lBrac) {this.lBrac = lBrac;}
    void setFunc(int func) {this.func = func;}
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
