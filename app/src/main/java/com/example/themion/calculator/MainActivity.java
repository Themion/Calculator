package com.example.themion.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
    final int ADD = 0;
    final int SUB = 1;
    final int MULT = 2;
    final int DIV = 3;
    final int EQU = 4;
    final String[] opSet = {" + ", " - ", " × ", " ÷ ", " = "};

    TextView edit, subEdit, valEdit;
    Button add, sub, mult, div, equ;
    Button cancel;

    int value = 5;
    boolean isThere = false;

    private LinkedList list = new LinkedList();
    private Node it = null;

    Button.OnClickListener mListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
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
                    it.setCalcOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_sub:
                    value = SUB;
                    it.setCalcOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_mult:
                    value = MULT;
                    it.setCalcOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_div:
                    value = DIV;
                    it.setCalcOp(value);

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_equ:
                    value = EQU;

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText(list.doCalc() + "");

                    it.setCalcOp(value);
                    isThere = true;

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (TextView) findViewById(R.id.edit);
        subEdit = (TextView) findViewById(R.id.subEdit);
        valEdit = (TextView) findViewById(R.id.valEdit);

        add = (Button) findViewById(R.id.btn_add);
        sub = (Button) findViewById(R.id.btn_sub);
        mult = (Button) findViewById(R.id.btn_mult);
        div = (Button) findViewById(R.id.btn_div);
        equ = (Button) findViewById(R.id.btn_equ);

        add.setOnClickListener(mListener);
        sub.setOnClickListener(mListener);
        mult.setOnClickListener(mListener);
        div.setOnClickListener(mListener);
        equ.setOnClickListener(mListener);

        cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                edit.setText("");
                subEdit.setText("");
                valEdit.setText("");
            }
        });
    }
/*
    public double doCalc() {
        double num = Double.parseDouble(edit.getText().toString());

        if (subEdit.getText() != "" && value != EQU) {
            switch (value) {
                case ADD:
                    num = Double.parseDouble(subEdit.getText().toString()) + num;
                    break;
                case SUB:
                    num = Double.parseDouble(subEdit.getText().toString()) - num;
                    break;
                case MULT:
                    num = Double.parseDouble(subEdit.getText().toString()) * num;
                    break;
                case DIV:
                    num = Double.parseDouble(subEdit.getText().toString()) / num;
                    break;
            }
        }

        return num;
    }
*/
    public void onClick(View v) {
        it = list.getLast();

        if (isThere)
        {
            edit.setText("");
            subEdit.setText("");
            valEdit.setText("");

            list = new LinkedList();

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
}