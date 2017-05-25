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
    final String[] opSet = {"　+", "　-", "　×", "　÷", "　="};

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

            switch (v.getId()) {
                case R.id.btn_add:
                    value = ADD;

                    valEdit.setText(opSet[value]);
                    subEdit.setText(list.print());
                    edit.setText("");

                    it.setCalcOp(value);
                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_sub:
                    value = SUB;

                    valEdit.setText(opSet[value]);
                    subEdit.setText(list.print());
                    edit.setText("");

                    it.setCalcOp(value);
                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_mult:
                    value = MULT;

                    valEdit.setText(opSet[value]);
                    subEdit.setText(list.print());
                    edit.setText("");

                    it.setCalcOp(value);
                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_div:
                    value = DIV;

                    valEdit.setText(opSet[value]);
                    subEdit.setText(list.print());
                    edit.setText("");

                    it.setCalcOp(value);
                    list.addNode();

                    isThere = false;

                    break;

                case R.id.btn_equ:
                    value = EQU;

                    valEdit.setText(opSet[value]);
                    subEdit.setText(list.print());
                    edit.setText(list.doCalc() + "");

                    it.setCalcOp(value);
                    isThere = true;

                    list = new LinkedList();

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

        if (isThere) {
            edit.setText("");
            subEdit.setText("");
            valEdit.setText("");
            isThere = false;
        }

        switch (v.getId()) {
            case R.id.btn_0:
                it.setPrintData(it.getPrintData() * 10 + 0);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_1:
                it.setPrintData(it.getPrintData() * 10 + 1);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_2:
                it.setPrintData(it.getPrintData() * 10 + 2);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_3:
                it.setPrintData(it.getPrintData() * 10 + 3);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_4:
                it.setPrintData(it.getPrintData() * 10 + 4);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_5:
                it.setPrintData(it.getPrintData() * 10 + 5);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_6:
                it.setPrintData(it.getPrintData() * 10 + 6);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_7:
                it.setPrintData(it.getPrintData() * 10 + 7);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_8:
                it.setPrintData(it.getPrintData() * 10 + 8);
                edit.setText(it.getPrintData() + "");
                break;
            case R.id.btn_9:
                it.setPrintData(it.getPrintData() * 10 + 9);
                edit.setText(it.getPrintData() + "");
                break;
        }
    }
}