package com.example.themion.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
    final int NOT_AN_OPERATOR = -1;
    final int ADD = 0;
    final int SUB = 1;
    final int MULT = 2;
    final int DIV = 3;
    final int EQU = 4;
    final String[] opSet = {" + ", " - ", " × ", " ÷ ", " = "};

    final int noBrac = 0;
    final int isBrac = 1;

    final int noCalc = 0;

    final int noF = 0;
    final int sinF = 1;
    final int cosF = 2;
    final int tanF = 3;
    final int powF = 4;

    TextView edit, subEdit;
    Button C;
    Button add, sub, mult, div, equ;
    Button lBrac, rBrac, squ, pow;
    Button sin, cos, tan, pi, deg;

    int value = NOT_AN_OPERATOR;

    boolean isThere = false;
    boolean ifPass = false;
    boolean ifPoint = false;

    private LinkedList list;
    private Node it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (TextView) findViewById(R.id.edit);
        subEdit = (TextView) findViewById(R.id.subEdit);

        add = (Button) findViewById(R.id.btn_add);
        sub = (Button) findViewById(R.id.btn_sub);
        mult = (Button) findViewById(R.id.btn_mult);
        div = (Button) findViewById(R.id.btn_div);
        equ = (Button) findViewById(R.id.btn_equ);

        add.setOnClickListener(opLsn);
        sub.setOnClickListener(opLsn);
        mult.setOnClickListener(opLsn);
        div.setOnClickListener(opLsn);
        equ.setOnClickListener(opLsn);

        lBrac = (Button) findViewById(R.id.btn_lBrac);
        rBrac = (Button) findViewById(R.id.btn_rBrac);
        squ = (Button) findViewById(R.id.btn_squ);
        pow = (Button) findViewById(R.id.btn_pow);

        rBrac.setOnClickListener(cLsn);
        lBrac.setOnClickListener(cLsn);
        squ.setOnClickListener(cLsn);
        pow.setOnClickListener(cLsn);

        sin = (Button) findViewById(R.id.btn_sin);
        cos = (Button) findViewById(R.id.btn_cos);
        tan = (Button) findViewById(R.id.btn_tan);
        pi = (Button) findViewById(R.id.btn_pi);
        deg = (Button) findViewById(R.id.btn_deg);

        sin.setOnClickListener(fLsn);
        cos.setOnClickListener(fLsn);
        tan.setOnClickListener(fLsn);
        pi.setOnClickListener(fLsn);
        deg.setOnClickListener(fLsn);

        C = (Button) findViewById(R.id.btn_C);
        C.setOnClickListener(cancelListener);

        list = new LinkedList();
        it = list.getLast();

        subEdit.setText("");
        edit.setText("");
    }

    public void onClick(View v)
    {
        it = list.getLast();

        if (isThere)
        {
            edit.setText("");
            subEdit.setText("");

            list = new LinkedList();
            it = list.getLast();

            isThere = false;
        }

        if((it.getPrev() != null) && ifPass)
        {
            value = MULT;
            subEdit.setText(subEdit.getText() + opSet[value]);

            list.addNode();
            it = it.getNext();
            it.setCalcOp(value);

            ifPass = false;
        }

        if(ifPoint) it.setPoint(it.getPoint() + 1);

        switch (v.getId())
        {
            case R.id.btn_0:
                it.setPrintData(it.getPrintData() * 10 + 0);
                subEdit.setText(subEdit.getText() + "0");

                break;

            case R.id.btn_1:
                it.setPrintData(it.getPrintData() * 10 + 1);
                subEdit.setText(subEdit.getText() + "1");

                break;

            case R.id.btn_2:
                it.setPrintData(it.getPrintData() * 10 + 2);
                subEdit.setText(subEdit.getText() + "2");

                break;

            case R.id.btn_3:
                it.setPrintData(it.getPrintData() * 10 + 3);
                subEdit.setText(subEdit.getText() + "3");

                break;

            case R.id.btn_4:
                it.setPrintData(it.getPrintData() * 10 + 4);
                subEdit.setText(subEdit.getText() + "4");

                break;

            case R.id.btn_5:
                it.setPrintData(it.getPrintData() * 10 + 5);
                subEdit.setText(subEdit.getText() + "5");

                break;

            case R.id.btn_6:
                it.setPrintData(it.getPrintData() * 10 + 6);
                subEdit.setText(subEdit.getText() + "6");

                break;

            case R.id.btn_7:
                it.setPrintData(it.getPrintData() * 10 + 7);
                subEdit.setText(subEdit.getText() + "7");

                break;

            case R.id.btn_8:
                it.setPrintData(it.getPrintData() * 10 + 8);
                subEdit.setText(subEdit.getText() + "8");

                break;

            case R.id.btn_9:
                it.setPrintData(it.getPrintData() * 10 + 9);
                subEdit.setText(subEdit.getText() + "9");

                break;

            case R.id.btn_point:
                if(!ifPoint)
                {
                    ifPoint = true;
                    subEdit.setText(subEdit.getText() + ".");
                }

                break;
        }
    }

    Button.OnClickListener opLsn = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            // TODO Auto-generated method stub

            if (isThere)
            {
                double data = list.doCalc();
                subEdit.setText("" + data);
                edit.setText("");

                list = new LinkedList();
                it = list.getLast();
                it.setPrintData(data);

                isThere = false;
                ifPoint = false;
            }

            switch (v.getId()) {
                case R.id.btn_add:
                    list.addNode();

                    value = ADD;
                    it.setPrintOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_sub:
                    list.addNode();

                    value = SUB;
                    it.setPrintOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_mult:
                    list.addNode();

                    value = MULT;
                    it.setPrintOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_div:
                    list.addNode();

                    value = DIV;
                    it.setPrintOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                break;

                case R.id.btn_equ:
                    value = EQU;

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText(list.doCalc() + "");
                    list.restore();

                    isThere = true;

                    break;
            }
        }
    };

    Button.OnClickListener cLsn = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            it = list.getLast();

            if (isThere)
            {
                double data = list.doCalc();
                subEdit.setText("");
                edit.setText("");

                list = new LinkedList();
                it = list.getLast();

                it.setPrintData(data);
                subEdit.setText(data + "");

                isThere = false;
                ifPoint = false;
            }

            switch(v.getId())
            {
                /*
                case R.id.btn_lBrac:
                    it.setLBrac(it.getLBrac() + 1);

                    subEdit.setText(subEdit.getText() + "(");

                    break;

                case R.id.btn_rBrac:
                    it.setRBrac(it.getRBrac() + 1);

                    subEdit.setText(subEdit.getText() + ")");

                    break;
                */
                case R.id.btn_squ:
                    it.setFunc(powF);
                    list.addNode();
                    it = it.getNext();
                    it.setCalcData(2);

                    subEdit.setText(subEdit.getText() + "^2");

                    ifPass = true;

                    break;

                case R.id.btn_pow:
                    it.setFunc(powF);
                    list.addNode();
                    it = it.getNext();

                    subEdit.setText(subEdit.getText() + "^");

                    ifPoint = false;

                    break;
            }
        }
    };

    Button.OnClickListener fLsn = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            it = list.getLast();

            if (isThere)
            {
                double data = list.doCalc();
                subEdit.setText("");
                edit.setText("");

                list = new LinkedList();
                it = list.getLast();

                isThere = false;
                ifPoint = false;
            }

            switch(v.getId())
            {
                case R.id.btn_sin:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;

                        subEdit.setText(list.print() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                        it.setPrintOp(value);
                        it = it.getNext();
                    }

                    it.setFunc(sinF);
                    list.addNode();
                    subEdit.setText(subEdit.getText() + "sin");

                    ifPoint = false;

                    break;

                case R.id.btn_cos:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;

                        subEdit.setText(list.print() + opSet[value]);

                        subEdit.setText(list.print() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                        it.setPrintOp(value);
                        it = it.getNext();
                    }

                    it.setFunc(cosF);
                    list.addNode();
                    subEdit.setText(subEdit.getText() + "cos");

                    ifPoint = false;

                    break;

                case R.id.btn_tan:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;
                        it.setPrintOp(value);

                        subEdit.setText(list.print() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                        it.setPrintOp(value);
                        it = it.getNext();
                    }

                    it.setFunc(tanF);
                    list.addNode();
                    subEdit.setText(subEdit.getText() + "tan");

                    ifPoint = false;

                    break;

                case R.id.btn_pi:
                    if(it.getPrintData() != 0)
                    {
                        value = MULT;
                        subEdit.setText(subEdit.getText() + opSet[value]);

                        list.addNode();
                        it = it.getNext();
                        it.setCalcOp(value);
                    }

                    it.setPrintData(Math.PI);

                    subEdit.setText(subEdit.getText() + "π");

                    ifPass = true;
                    ifPoint = false;

                    break;

                case R.id.btn_deg:
                    list.setDeg(!list.getDeg());

                    if(list.getDeg()) deg.setText("DEG");
                    else deg.setText("RAD");

                    break;

            }
        }
    };

    Button.OnClickListener cancelListener = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            edit.setText("");
            subEdit.setText("");
            deg.setText("RAD");

            list = new LinkedList();
            list.setDeg(false);
            it = list.getLast();
            it.setPrintOp(NOT_AN_OPERATOR);
            isThere = false;
            ifPass = false;
            ifPoint = false;
        }
    };
}