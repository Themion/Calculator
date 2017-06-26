package com.example.themion.calculator;

public class CalculateList
{
    final int ADD = 0;
    final int SUB = 1;
    final int MULT = 2;
    final int DIV = 3;
    final int POW = 4;
    final int EQU = 5;

    final int noF = 0;
    final int sinF = 1;
    final int cosF = 2;
    final int tanF = 3;
    final int logF = 4;
    final int lnF = 5;
    final int absF = 6;

    final String[] opSet = {" + ", " - ", " × ", " ÷ ", "^", " = "};
    final String[] funcSet = {"(", "sin(", "cos(", "tan(", "log(", "ln(", "abs("};

/*------------------------------------------------------------------------------------------------------------------------------*/

    private boolean ifDeg = false;

    private Node first, last, mother;

    CalculateList()
    {
        this.ifDeg = false;

        this.first = new Node();
        this.last = this.first;
        this.mother = null;

        this.first.setMotherList(this);
    }

    boolean getDeg() {return this.ifDeg;}

    Node getFirst() {return this.first;}
    Node getLast() {return this.last;}
    Node getMother() {return this.mother;}

    void setDeg(boolean ifDeg) {this.ifDeg = ifDeg;}

    void setFirst(Node first) {this.first = first;}
    void setLast(Node last) {this.last = last;}
    void setMother(Node mother) {this.mother = mother;}

    void addNode()
    {
        if(this.getFirst() == null)
        {
            this.setFirst(new Node());
            this.setLast(this.getFirst());
        }

        else
        {
            this.getLast().setNext(new Node());
            this.getLast().getNext().setPrev(this.getLast());
            this.setLast(this.getLast().getNext());
        }

        this.getLast().setMotherList(this);
        this.getLast().setNext(null);
    }

    void popNode()
    {
        if(this.getFirst() == null) return;

        this.setLast(this.getLast().getPrev());
        this.getLast().getNext().setPrev(null);
        this.getLast().setNext(null);
    }

/*------------------------------------------------------------------------------------------------------------------------------*/

    double doCalc()
    {
        Node it = new Node();
        it.setNext(this.getFirst());

        while(it != this.getLast())
        {
            boolean ifOverSingularity = false;

            it = it.getNext();

            if(it.getPoint() > 0) it.setCalcData(it.getCalcData() / Math.pow(10, it.getPoint() - 1));
            if(it.getPM()) it.setCalcData(it.getCalcData() * (-1));
            if((it.getPrev() != null) && (it.getPrev().getPrintOp() == DIV)) it.setCalcData(1 / (it.getCalcData()));

            switch(it.getFunc())
            {
                case (sinF):
                    if(getDeg()) it.setCalcData(Math.toRadians(it.getCalcData()));

                    while(it.getCalcData() < 0) it.setCalcData(it.getCalcData() + (2 * Math.PI));
                    while(it.getCalcData() >= 2 * Math.PI) it.setCalcData(it.getCalcData() - (2 * Math.PI));

                    if(it.getCalcData() > Math.PI)
                    {
                        ifOverSingularity = true;
                        it.setCalcData(it.getCalcData() - (Math.PI));
                    }

                    if(it.getCalcData() > (Math.PI / 2)) it.setCalcData(Math.PI - it.getCalcData());

                    it.setCalcData(Math.sin(it.getCalcData()));
                    it.setCalcData(it.getCalcData() / Math.sin(Math.PI / 2));

                    if(ifOverSingularity) it.setCalcData(it.getCalcData() * (-1));

                    break;

                case (cosF):
                    if(getDeg()) it.setCalcData(Math.toRadians(it.getCalcData()));

                    while(it.getCalcData() < 0) it.setCalcData(it.getCalcData() + (2 * Math.PI));
                    while(it.getCalcData() >= 2 * Math.PI) it.setCalcData(it.getCalcData() - (2 * Math.PI));

                    if(it.getCalcData() > Math.PI) it.setCalcData((2 * Math.PI) - it.getCalcData());

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

                    while(it.getCalcData() < 0) it.setCalcData(it.getCalcData() + (Math.PI));
                    while(it.getCalcData() >= Math.PI) it.setCalcData(it.getCalcData() - (Math.PI));

                    if(it.getCalcData() > (Math.PI / 2))
                    {
                        ifOverSingularity = true;
                        it.setCalcData(Math.PI - it.getCalcData());
                    }

                    it.setCalcData(Math.tan(it.getCalcData()));
                    it.setCalcData(it.getCalcData() / Math.tan(Math.PI / 4));

                    if(ifOverSingularity) it.setCalcData(it.getCalcData() * (-1));

                    break;

                case (logF):
                    it.setCalcData(Math.log10(it.getCalcData()));
                    break;

                case (lnF):
                    it.setCalcData(Math.log(it.getCalcData()));
                    break;

                case (absF):
                    it.setCalcData(Math.abs(it.getCalcData()));
                    break;
            }
        }

/*------------------------------------------------------------------------------------------------------------------------------*/

        if(this.getFirst() == this.getLast()) return this.getFirst().getCalcData();

/*------------------------------------------------------------------------------------------------------------------------------*/

        it = this.getFirst();

        while(it != this.getLast())
        {
            switch(it.getCalcOp())
            {
                case (POW):
                    it.getNext().setCalcData(Math.pow(it.getCalcData(), it.getNext().getCalcData()));
                    it.setCalcOp(MULT);
                    it.setCalcData(1);

                    break;
            }

            it = it.getNext();
        }

        it = this.getFirst();

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
            }

            it = it.getNext();
        }

/*------------------------------------------------------------------------------------------------------------------------------*/

        it = this.getFirst();

        while(it != this.getLast())
        {
            switch(it.getCalcOp())
            {
                case (ADD):
                    it.getNext().setCalcData(it.getCalcData() + it.getNext().getCalcData());

                    break;
            }

            it = it.getNext();
        }

        it = this.getFirst();

        while(it != this.getLast())
        {
            it.setCalcData(it.getPrintData());
            it.setCalcOp(it.getPrintOp());

            it = it.getNext();
        }

        it.setCalcOp(-1);

        return this.getLast().getCalcData();
    }

/*------------------------------------------------------------------------------------------------------------------------------*/

    String print()
    {
        Node it = this.getFirst();
        String text = "";

        while(it != null)
        {
            if(it.getPoint() > 0) it.setPrintData(it.getPrintData() / Math.pow(10, it.getPoint() - 1));
            it = it.getNext();
        }

        it = this.getFirst();

        while(it != null)
        {
            if(it.getBracList() != null)
            {
                text += funcSet[it.getFunc()] + it.getBracList().print();
                if(it.getBracList().getLast().getPrintOp() == EQU) text += ")";
            }

            else
            {
                if(it.getPM() && !((it.getPrev() != null) && (it.getPrev().getCalcOp() == ADD))) text += "-";

                if(it.getPrintData() == Math.PI) text += "π";
                else if(it.getPrintData() == Math.E) text += "e";
                else if(!((it.getNext() == null) && (!it.getHit())))
                {
                    if (it.getPoint() == 1) text = text + String.valueOf((int) it.getPrintData()) + '.';
                    else if (it.getPrintData() == (int) it.getPrintData()) text = text + String.valueOf((int) it.getPrintData());
                    else text += String.valueOf(it.getPrintData());
                }
            }

            if((it.getNext() != null) && (it.getPrintOp() != -1)) text += opSet[it.getPrintOp()];

            it = it.getNext();
        }

        it = this.getFirst();

        while(it != null)
        {
            if(it.getPoint() > 0) it.setPrintData(it.getPrintData() * Math.pow(10, it.getPoint() - 1));
            it = it.getNext();
        }

        return text;
    }
}