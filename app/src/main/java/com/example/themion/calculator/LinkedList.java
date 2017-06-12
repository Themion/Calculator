package com.example.themion.calculator;

public class LinkedList
{
    final int ADD = 0;
    final int SUB = 1;
    final int MULT = 2;
    final int DIV = 3;
    final int POW = 4;
    final int EQU = 5;

    final int sinF = 1;
    final int cosF = 2;
    final int tanF = 3;
    final int logF = 4;
    final int lnF = 5;

/*------------------------------------------------------------------------------------------------------------------------------*/

    private boolean ifDeg = false;

    private double dataCalc;
    private int len;
    private Node list;
    private Node first, last, mother;

    LinkedList()
    {
        this.dataCalc = 0;
        this.len = 1;

        this.list = new Node();
        this.first = this.list;
        this.last = this.list;
        this.mother = null;
        this.first.setMotherList(this);
    }

    boolean getDeg() {return this.ifDeg;}

    Node getFirst() {return this.first;}
    Node getLast() {return this.last;}
    Node getMother() {return this.mother;}

    void setMother(Node mother) {this.mother = mother;}
    void setDeg(boolean ifDeg) {this.ifDeg = ifDeg;}

    void addNode()
    {
        if(this.list == null)
        {
            this.list = new Node();
            this.list.setMotherList(this);
            this.first = this.list;
            this.last = this.list;
        }

        else
        {
            this.last.setNext(new Node());
            this.last.getNext().setPrev(this.last);
            this.last = this.last.getNext();
            this.last.setMotherList(this);
        }

        this.last.setNext(null);
        this.len++;
    }

/*------------------------------------------------------------------------------------------------------------------------------*/

    double doCalc()
    {
        this.dataCalc = 0;

        Node it = this.first;

        while(it != null)
        {
            it.setCalcData(it.getCalcData() / Math.pow(10, it.getPoint()));
            it = it.getNext();
        }

        it = this.first;

        while(it != null)
        {
            if(it.getPM()) it.setCalcData(it.getCalcData() * (-1));
            it = it.getNext();
        }

/*------------------------------------------------------------------------------------------------------------------------------*/

        it = new Node();
        it.setNext(this.getFirst());

        while(it != this.getLast())
        {
            boolean ifOverSingularity = false;

            it = it.getNext();

            switch(it.getFunc())
            {
                case (sinF):
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
                    it.setCalcData(it.getCalcData() / Math.sin(Math.PI / 2));

                    if(ifOverSingularity) it.setCalcData(it.getCalcData() * (-1));

                    break;

                case (cosF):
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

                    it.setCalcData(Math.cos(it.getCalcData()));
                    it.setCalcData(it.getCalcData() / Math.cos(0));

                    if(ifOverSingularity) it.setCalcData(it.getCalcData() * (-1));

                    break;

                case (tanF):
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

                    it.setCalcData(Math.tan(it.getCalcData()));
                    it.setCalcData(it.getCalcData() / Math.tan(Math.PI / 4));

                    if(ifOverSingularity) it.setCalcData(it.getCalcData() * (-1));

                    break;

                case (logF):
                    if(it.getCalcData() > 0)
                        it.setCalcData(Math.log10(it.getCalcData()));

                    break;

                case (lnF):
                    if(it.getCalcData() > 0)
                        it.setCalcData(Math.log(it.getCalcData()));

                    break;
            }
        }

/*------------------------------------------------------------------------------------------------------------------------------*/

        if(this.len == 1)
        {
            this.dataCalc = this.first.getCalcData();
            return this.dataCalc;
        }

/*------------------------------------------------------------------------------------------------------------------------------*/

        it = this.first;

        while(it != this.getLast())
        {
            switch(it.getCalcOp())
            {
                case (POW):
                    it.getNext().setCalcData(Math.pow(it.getCalcData(), it.getNext().getCalcData()));
                    it.setCalcOp(ADD);
                    it.setCalcData(0);

                    break;
            }

            it = it.getNext();
        }

        it = this.first;

/*------------------------------------------------------------------------------------------------------------------------------*/

        while(it != this.getLast())
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

/*------------------------------------------------------------------------------------------------------------------------------*/

        it = this.first;

        while(it != this.getLast())
        {
            switch(it.getCalcOp())
            {
                case (ADD):
                    it.getNext().setCalcData(it.getCalcData() + it.getNext().getCalcData());
                    it.setCalcOp(ADD);
                    it.setCalcData(0);

                    break;
            }

            it = it.getNext();
        }

        this.first.setCalcData(this.getLast().getCalcData());
        this.last.setCalcData(0);
        this.dataCalc = this.first.getCalcData();

        return this.dataCalc;
    }

/*------------------------------------------------------------------------------------------------------------------------------*/

    void restore()
    {
        Node it = this.getFirst();

        while(it != null)
        {
            it.setCalcData(it.getPrintData());
            it.setCalcOp(it.getPrintOp());

            it = it.getNext();
        }
    }

/*------------------------------------------------------------------------------------------------------------------------------*/

    String print()
    {
        final String[] opSet = {" + ", " - ", " × ", " ÷ ", "^", " = "};
        final String[] funcSet = {"(", "sin(", "cos(", "tan(", "log(", "ln("};

        Node it = this.getFirst();
        String text = "";

        while(it != null)
        {
            if(it.getBracList() != null) text += funcSet[it.getFunc()] + it.getBracList().print() + ")";

            else
            {
                if(it.getPM()) text += " - ";

                if(it.getPrintData() == Math.PI) text += "π";
                else if(it.getPrintData() == Math.E) text += "e";
                else
                {
                    if(it.getPoint() == 0) text += (int) it.getPrintData();
                    else text += it.getPrintData();
                }
            }

            if((it.getNext() != null) && (it.getPrintOp() != -1) && !((it.getNext().getPM()) && (it.getPrintOp() == ADD))) text += opSet[it.getPrintOp()];

            it = it.getNext();
        }

        if(this.getMother() == null) text += opSet[EQU];
        return text;
    }
}