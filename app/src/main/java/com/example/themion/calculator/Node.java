package com.example.themion.calculator;

/**
 * Created by Themion on 2017-05-24.
 */

class Node {
    final int NOT_AN_OPERATOR = -1;

    final int noFunc = 0;

/*------------------------------------------------------------------------------------------------------------------------------*/

    private double printData;
    private double calcData;

    private int calcOp;
    private int printOp;
    private int point;
    private int func;

    private boolean pm;
    private boolean hit;

    private Node next;
    private Node prev;
    private CalculateList motherList;
    private CalculateList bracList;

/*------------------------------------------------------------------------------------------------------------------------------*/

    Node()
    {
        this.printData = 0;
        this.calcData = 0;

        this.printOp = NOT_AN_OPERATOR;
        this.calcOp = NOT_AN_OPERATOR;
        this.point = 0;
        this.func = noFunc;

        this.pm = false; //plus or minus
        this.hit = false; //if calculation butten is hit

        this.next = null;
        this.prev = null;
        this.motherList = null;
        this.bracList = null;
    }

/*------------------------------------------------------------------------------------------------------------------------------*/

    double getPrintData() {return this.printData;}
    double getCalcData() {return this.calcData;}

    int getPrintOp() {return this.printOp;}
    int getCalcOp() {return this.calcOp;}
    int getPoint() {return this.point;}
    int getFunc() {return this.func;}

    boolean getPM() {return this.pm;}
    boolean getHit() {return this.hit;}

    Node getNext() {return this.next;}
    Node getPrev() {return this.prev;}
    CalculateList getMotherList() {return this.motherList;}
    CalculateList getBracList() {return this.bracList;}

/*------------------------------------------------------------------------------------------------------------------------------*/

    void setPrintData(double printData) {this.printData = printData; this.calcData = printData;}
    void setCalcData(double calcData) {this.calcData = calcData;}

    void setPrintOp(int printOp) {this.printOp = printOp; this.calcOp = printOp;}
    void setCalcOp(int calcOp) {this.calcOp = calcOp;}
    void setPoint(int point) {this.point = point;}
    void setFunc(int func) {this.func = func;}

    void setPM(boolean pm) {this.pm = pm;}
    void setHit(boolean hit) {this.hit = hit;}

    void setNext(Node next) {this.next = next;}
    void setPrev(Node prev) {this.prev = prev;}
    void setMotherList(CalculateList motherList) {this.motherList = motherList;}
    void setBracList(CalculateList bracList) {this.bracList = bracList;}
}
