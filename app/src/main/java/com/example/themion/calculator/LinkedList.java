package com.example.themion.calculator;

public class LinkedList extends Node
{
    final int ADD = 0;
    final int SUB = 1;
    final int MULT = 2;
    final int DIV = 3;
    final int EQU = 4;

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

    Node getLast() {return this.last;}
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

    public double doCalc()
    {
        this.dataCalc = 0;

        if(this.len == 1)
        {
            this.dataCalc = this.list.getCalcData();
            return this.dataCalc;
        }

        Node it = this.last;

        while(it.getPrev() != null)
        {
            switch(it.getPrev().getCalcOp())
            {
                case (MULT):
                    it.getPrev().setCalcData(it.getPrev().getCalcData() * it.getCalcData());
                    it.setCalcOp(it.getPrev().getCalcOp());
                    it.setCalcData(0);
                    it.setCalcOp(ADD);
                    break;

                case (DIV):
                    it.getPrev().setCalcData(it.getPrev().getCalcData() / it.getCalcData());
                    it.setCalcOp(it.getPrev().getCalcOp());
                    it.setCalcData(0);
                    it.setCalcOp(ADD);
                    break;
            }

            it = it.getPrev();
        }

        it = this.last;

        while(it.getPrev() != null)
        {
            switch(it.getPrev().getCalcOp())
            {
                case (ADD):
                    it.getPrev().setCalcData(it.getPrev().getCalcData() + it.getCalcData());
                    it.setCalcOp(it.getPrev().getCalcOp());
                    it.setCalcData(0);
                    it.setCalcOp(ADD);
                    break;

                case (SUB):
                    it.getPrev().setCalcData(it.getPrev().getCalcData() - it.getCalcData());
                    it.setCalcOp(it.getPrev().getCalcOp());
                    it.setCalcData(0);
                    it.setCalcOp(ADD);
                    break;
            }

            it = it.getPrev();
        }

        it = this.last;
        this.dataCalc = this.first.getCalcData();

        while(it.getPrev() != null)
        {
            it.setCalcData(it.getPrintData());
            it.setCalcOp(it.getPrintOp());
            it = it.getPrev();
        }

        it.setCalcData(it.getPrintData());
        it.setCalcOp(it.getPrintOp());

        return this.dataCalc;
    }

    String print()
    {
        Node it = this.first;
        String prt = "";
        int temp = this.last.getPrintOp();

        this.last.setPrintOp(EQU);

        while(it != this.last)
        {
            prt += "" +  it.getPrintData() + this.opSet[it.getPrintOp()];
            it = it.getNext();
        }

        prt += "" + this.last.getPrintData();

        this.last.setPrintOp(temp);
        return prt;
    }
}