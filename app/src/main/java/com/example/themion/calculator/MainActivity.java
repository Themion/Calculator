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

    final int noF = 0;
    final int sinF = 1;
    final int cosF = 2;
    final int tanF = 3;

    TextView edit, subEdit;
    Button add, sub, mult, div, equ;
    Button C;
    Button sin, cos, tan, pi, deg;

    int value = NOT_AN_OPERATOR;
    boolean isThere = false;

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
        C.setOnClickListener(cLsn);

        list = new LinkedList();
        it = list.getLast();
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

        switch (v.getId()) {
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
            }

            switch (v.getId()) {
                case R.id.btn_add:
                    value = ADD;
                    it.setPrintOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_sub:
                    value = SUB;
                    it.setPrintOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_mult:
                    value = MULT;
                    it.setPrintOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_div:
                    value = DIV;
                    it.setPrintOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    list.addNode();

                    isThere = false;

                break;

                case R.id.btn_equ:
                    value = EQU;

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText(list.doCalc() + "");

                    it.setPrintOp(value);
                    isThere = true;

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

                it.setPrintData(data);
                subEdit.setText(data + "");

                isThere = false;
            }

            switch(v.getId())
            {
                case R.id.btn_sin:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;
                        it.setPrintOp(value);

                        subEdit.setText(list.print() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                    }

                    it.setFunc(sinF);
                    subEdit.setText(subEdit.getText() + "sin");

                    break;

                case R.id.btn_cos:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;
                        it.setPrintOp(value);

                        subEdit.setText(list.print() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                    }

                    it.setFunc(cosF);
                    subEdit.setText(subEdit.getText() + "cos");

                    break;

                case R.id.btn_tan:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;
                        it.setPrintOp(value);

                        subEdit.setText(list.print() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                    }

                    it.setFunc(tanF);
                    subEdit.setText(subEdit.getText() + "tan");

                    break;

                case R.id.btn_pi:
                    if(it.getPrintData() == 0) it.setPrintData(Math.PI);
                    else it.setPrintData(it.getPrintData() * Math.PI);

                    subEdit.setText(subEdit.getText() + "π");

                    break;

                case R.id.btn_deg:
                    list.setDeg(!list.getDeg());

                    if(list.getDeg()) deg.setText("DEG");
                    else deg.setText("RAD");

                    edit.setText("" + list.getDeg());

                    break;

            }
        }
    };

    Button.OnClickListener cLsn = new Button.OnClickListener()
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
        }
    };
}