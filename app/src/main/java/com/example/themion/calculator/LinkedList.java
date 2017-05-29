package com.example.themion.calculator;

public class LinkedList
{
    final int NOT_AN_OPERATOR = -1;
    final int ADD = 0;
    final int SUB = 1;
    final int MULT = 2;
    final int DIV = 3;
    final int EQU = 4;

    final int noBrac = 0;
    final int rightBrac = 1;
    final int leftBrac = 2;

    final int noFunc = 0;
    final int sinFunc = 1;
    final int cosFunc = 2;
    final int tanFunc = 3;

    private boolean ifDeg = false;

    private double dataCalc;
    private int len;
    private Node list;
    private Node first, last;
    private String[] opSet = {" + ", " - ", " × ", " ÷ ", " = "};

    LinkedList()
    {
        this.dataCalc = 0;
        this.len = 1;
        this.list = new Node();
        this.first = this.list;
        this.last = this.list;
    }

    int getLen() {return this.len;}
    Node getLast() {return this.last;}
    boolean getDeg() {return this.ifDeg;}

    void setDeg(boolean ifDeg) {this.ifDeg = ifDeg;}
/*
    node seek(int index)
    {
        node ret = this.first;

        for(int i = 0; i < index; i++) ret = ret.next;

        return ret;
    }
*/

    void addNode()
    {
        if(this.list == null)
        {
            this.list = new Node();
            this.first = this.list;
            this.last = this.list;

            this.first.setPrev(null);
        }

        else
        {
            this.last.setNext(new Node());
            this.last.getNext().setPrev(this.last);
            this.last = this.last.getNext();
        }

        this.last.setNext(null);
        this.len++;
    }

    double doCalc()
    {
        this.dataCalc = 0;

        Node it = this.first;

        while(it != null)
        {
            boolean ifOverSingularity = false;

            switch(it.getFunc())
            {
                case (sinFunc):
                    if(getDeg()) it.setCalcData(Math.toRadians(it.getCalcData()));

                    while(it.getCalcData() < 0)
                        it.setCalcData(it.getCalcData() + (2 * Math.PI));

                    while(it.getCalcData() >= 2 * Math.PI)
                        it.setCalcData(it.getCalcData() - (2 * Math.PI));

                    if(it.getCalcData() > Math.PI)
                    {
                        ifOverSingularity = true;
                        it.setCalcData(it.getCalcData() - (Math.PI));
                    }

                    if(it.getCalcData() > (Math.PI / 2))
                        it.setCalcData(Math.PI - it.getCalcData());

                    it.setCalcData(Math.sin(it.getCalcData()));

                    if(ifOverSingularity) it.setCalcData(it.getCalcData() * (-1));

                    break;

                case (cosFunc):
                    if(getDeg()) it.setCalcData(Math.toRadians(it.getCalcData()));

                    while(it.getCalcData() < 0)
                        it.setCalcData(it.getCalcData() + (2 * Math.PI));

                    while(it.getCalcData() >= 2 * Math.PI)
                        it.setCalcData(it.getCalcData() - (2 * Math.PI));

                    if(it.getCalcData() > Math.PI)
                        it.setCalcData(it.getCalcData() - (Math.PI));

                    if(it.getCalcData() > (Math.PI / 2))
                    {
                        ifOverSingularity = true;
                        it.setCalcData(Math.PI - it.getCalcData());
                    }

                    it.setCalcData(Math.cos(it.getCalcData()) / Math.cos(Math.toRadians(90)));

                    if(ifOverSingularity) it.setCalcData(it.getCalcData() * (-1));

                    break;

                case (tanFunc):
                    if(getDeg()) it.setCalcData(Math.toRadians(it.getCalcData()));

                    while(it.getCalcData() < 0)
                        it.setCalcData(it.getCalcData() + (Math.PI));

                    while(it.getCalcData() >= Math.PI)
                        it.setCalcData(it.getCalcData() - (Math.PI));

                    if(it.getCalcData() > (Math.PI / 2))
                    {
                        ifOverSingularity = true;
                        it.setCalcData(it.getCalcData() - (Math.PI / 2));
                    }

                    it.setCalcData(Math.tan(it.getCalcData()) / Math.tan(Math.PI / 4));

                    if(ifOverSingularity) it.setCalcData(it.getCalcData() * (-1));

                    break;
            }

            it = it.getNext();
        }

        if(this.len == 1)
        {
            this.dataCalc = this.first.getCalcData();
            return this.dataCalc;
        }

        it = this.first;

        while(it.getNext() != null)
        {
            switch(it.getCalcOp())
            {
                case (MULT):
                    it.getNext().setCalcData(it.getCalcData() * it.getNext().getCalcData());
                    it.setCalcOp(ADD);
                    it.setCalcData(0);

                    break;

                case (DIV):
                    it.getNext().setCalcData(it.getCalcData() / it.getNext().getCalcData());
                    it.setCalcOp(ADD);
                    it.setCalcData(0);

                    break;
            }

            it = it.getNext();
        }

        it = this.first;

        while(it.getNext() != null)
        {
            switch(it.getCalcOp())
            {
                case (ADD):
                    it.getNext().setCalcData(it.getCalcData() + it.getNext().getCalcData());
                    it.setCalcOp(ADD);
                    it.setCalcData(0);

                    break;

                case (SUB):
                    it.getNext().setCalcData(it.getCalcData() - it.getNext().getCalcData());
                    it.setCalcOp(ADD);
                    it.setCalcData(0);

                    break;
            }

            it = it.getNext();
        }

        this.dataCalc = this.last.getCalcData();
        it = this.first;

        while(it != null)
        {
            it.setCalcData(it.getPrintData());
            it.setCalcOp(it.getPrintOp());
            it = it.getNext();
        }

        return this.dataCalc;
    }

    String print()
    {
        Node it = this.first;
        String prt = "";

        this.last.setPrintOp(EQU);

        while(it.getNext() != null)
        {
            prt += "" +  it.getPrintData() + this.opSet[it.getPrintOp()];
            it = it.getNext();
        }

        prt += "" + this.last.getPrintData();

        return prt;
    }
}