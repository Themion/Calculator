package com.example.themion.calculator;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends Activity
{
    final int NOT_AN_OPERATOR = -1;
    final int ADD = 0;
    final int SUB = 1;
    final int MULT = 2;
    final int DIV = 3;
    final int POW = 4;
    final int EQU = 5;
    final String[] opSet = {" + ", " - ", " × ", " ÷ ", "^", " = "};

    final int noBrac = 0;
    final int isBrac = 1;

    final int noCalc = 0;

    final int noF = 0;
    final int sinF = 1;
    final int cosF = 2;
    final int tanF = 3;
    final int logF = 4;
    final int lnF = 5;

    /*------------------------------------*/

    TextView edit, subEdit;

    Button C;
    Button add, sub, mult, div, equ;
    Button lBrac, rBrac, squ, pow;
    Button log, ln;
    Button sin, cos, tan, deg;

    int value = NOT_AN_OPERATOR;

    boolean isThere = false;
    boolean ifPass = false;
    boolean ifPoint = false;

    private LinkedList list, temp = null;
    private Node it;

/*------------------------------------------------------------------------------------------------------------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (TextView) findViewById(R.id.edit);
        subEdit = (TextView) findViewById(R.id.subEdit);

/*------------------------------------------------------------------------------------------------------------------------------*/

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

 /*------------------------------------------------------------------------------------------------------------------------------*/

        lBrac = (Button) findViewById(R.id.btn_lBrac);
        rBrac = (Button) findViewById(R.id.btn_rBrac);
        squ = (Button) findViewById(R.id.btn_squ);
        pow = (Button) findViewById(R.id.btn_pow);

        lBrac.setOnClickListener(cLsn);
        rBrac.setOnClickListener(cLsn);
        squ.setOnClickListener(fLsn);
        pow.setOnClickListener(opLsn);

/*------------------------------------------------------------------------------------------------------------------------------*/

        log = (Button) findViewById(R.id.btn_log);
        ln = (Button) findViewById(R.id.btn_ln);

        log.setOnClickListener(fLsn);
        ln.setOnClickListener(fLsn);

/*------------------------------------------------------------------------------------------------------------------------------*/

        sin = (Button) findViewById(R.id.btn_sin);
        cos = (Button) findViewById(R.id.btn_cos);
        tan = (Button) findViewById(R.id.btn_tan);
        deg = (Button) findViewById(R.id.btn_deg);

        sin.setOnClickListener(fLsn);
        cos.setOnClickListener(fLsn);
        tan.setOnClickListener(fLsn);
        deg.setOnClickListener(fLsn);

/*------------------------------------------------------------------------------------------------------------------------------*/

        C = (Button) findViewById(R.id.btn_C);
        C.setOnClickListener(cancelListener);

        list = new LinkedList();
        it = list.getLast();

        subEdit.setText("");
        edit.setText("");
    }

/*------------------------------------------------------------------------------------------------------------------------------*/

    public void onClick(View v)
    {
        if(it.getNext() != null) it = it.getNext();

        if (isThere)
        {
            edit.setText("");
            subEdit.setText("");

            list = new LinkedList();
            it = list.getLast();

            isThere = false;
            ifPass = false;
            ifPoint = false;
        }

        if(ifPass)
        {
            value = MULT;
            subEdit.setText(subEdit.getText() + opSet[value]);
            it.setCalcOp(value);

            list.addNode();
            it = it.getNext();
            it.setCalcOp(value);

            ifPass = false;
        }

        if(ifPoint) it.setPoint(it.getPoint() + 1);

        switch (v.getId())
        {
            case R.id.btn_help:
                Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mathway.com/ko/FiniteMath"));
                link.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(link);

                break;

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

            case R.id.btn_pi:
                if(it.getPrintData() != 0)
                {
                    value = MULT;
                    subEdit.setText(subEdit.getText() + opSet[value]);

                    list.addNode();
                    it = it.getNext();
                    it.setCalcOp(value);
                }

                it.setPrintData(Math.E);

                subEdit.setText(subEdit.getText() + "e");

                isThere = false;
                ifPass = true;
                ifPoint = false;

                break;

            case R.id.btn_exp:
                if(it.getPrintData() != 0)
                {
                    value = MULT;
                    subEdit.setText(subEdit.getText() + opSet[value]);

                    list.addNode();
                    it = it.getNext();
                    it.setCalcOp(value);
                }

                it.setPrintData(Math.E);

                subEdit.setText(subEdit.getText() + "e");

                isThere = false;
                ifPass = true;
                ifPoint = false;

                break;

            case R.id.btn_point:
                if(!ifPoint)
                {
                    ifPoint = true;
                    subEdit.setText(subEdit.getText() + ".");
                }

            case R.id.btn_pm:
                if((it.getPrintData() != 0) || (list.getMother() != null)) break;

                if(!it.getPM())
                {
                    it.setPM(true);
                    subEdit.setText(subEdit.getText() + "-(");

                    it.setBracList(new LinkedList());
                    it.getBracList().setMother(it);

                    temp = list;
                    list = it.getBracList();
                    it = it.getBracList().getFirst();

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;
                }

                break;
        }
    }

//*------------------------------------------------------------------------------------------------------------------------------*/

    Button.OnClickListener opLsn = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            // TODO Auto-generated method stub

            if (isThere)
            {
                double data = Double.parseDouble(edit.getText().toString());
                subEdit.setText("" + data);
                edit.setText("");

                list = new LinkedList();
                it = list.getLast();
                it.setPrintData(data);

                isThere = false;
                ifPass = false;
                ifPoint = false;
            }

            switch (v.getId()) {
                case R.id.btn_add:
                    value = ADD;
                    it.setPrintOp(value);

                    list.addNode();
                    it = it.getNext();

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_sub:
                    value = SUB;
                    it.setPrintOp(value);

                    list.addNode();
                    it = it.getNext();

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_mult:
                     value = MULT;
                    it.setPrintOp(value);

                    list.addNode();
                    it = it.getNext();

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_div:
                    value = DIV;
                    it.setPrintOp(value);

                    list.addNode();
                    it = it.getNext();

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                break;

                case R.id.btn_pow:
                    value = POW;
                    it.setPrintOp(value);

                    list.addNode();
                    it = it.getNext();

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText("");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_equ:
                    while(list.getMother() != null)
                    {
                        it = list.getMother();
                        it.setPrintData(it.getBracList().doCalc());

                        list = temp;
                        temp = null;

                        subEdit.setText(subEdit.getText() + ")");
                    }

                    value = EQU;

                    subEdit.setText(subEdit.getText() + opSet[value]);
                    edit.setText(list.doCalc() + "");

                    isThere = true;
                    ifPass = false;
                    ifPoint = false;

                    break;
            }
        }
    };

/*------------------------------------------------------------------------------------------------------------------------------*/

    Button.OnClickListener cLsn = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
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
                ifPass = false;
                ifPoint = false;
            }

            switch(v.getId())
            {
                case R.id.btn_lBrac:
                    if(it.getPrintData() != 0)
                    {
                        value = MULT;
                        subEdit.setText(subEdit.getText() + opSet[value]);

                        it.setCalcOp(value);
                        list.addNode();
                        it = it.getNext();
                    }

                    it.setBracList(new LinkedList());
                    it.getBracList().setMother(it);

                    temp = list;
                    list = it.getBracList();
                    it = it.getBracList().getFirst();

                    subEdit.setText(subEdit.getText() + "(");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_rBrac:
                    it = list.getMother();
                    it.setPrintData(it.getBracList().doCalc());

                    list = temp;
                    temp = null;

                    subEdit.setText(subEdit.getText() + ")");

                    isThere = false;
                    ifPass = true;
                    ifPoint = false;

                    break;
            }
        }
    };

/*------------------------------------------------------------------------------------------------------------------------------*/

    Button.OnClickListener fLsn = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            if (isThere)
            {
                double data = list.doCalc();
                subEdit.setText("");
                edit.setText("");

                list = new LinkedList();
                it = list.getLast();

                isThere = false;
                ifPass = false;
                ifPoint = false;
            }

            switch(v.getId())
            {
                case R.id.btn_sin:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;

                        subEdit.setText(subEdit.getText() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                        it.setPrintOp(value);
                        it = it.getNext();
                    }

                    it.setFunc(sinF);
                    subEdit.setText(subEdit.getText() + "sin");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_cos:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;

                        subEdit.setText(subEdit.getText() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                        it.setPrintOp(value);
                        it = it.getNext();
                    }

                    it.setFunc(cosF);
                    subEdit.setText(subEdit.getText() + "cos");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_tan:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;
                        it.setPrintOp(value);

                        subEdit.setText(subEdit.getText() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                        it.setPrintOp(value);
                        it = it.getNext();
                    }

                    it.setFunc(tanF);
                    subEdit.setText(subEdit.getText() + "tan");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_squ:
                    value = POW;
                    it.setPrintOp(value);

                    list.addNode();
                    it = it.getNext();
                    it.setPrintData(2);

                    subEdit.setText(subEdit.getText() + opSet[value] + "2");
                    edit.setText("");

                    isThere = false;
                    ifPass = true;
                    ifPoint = false;

                    break;

                case R.id.btn_log:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;
                        it.setPrintOp(value);

                        subEdit.setText(subEdit.getText() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                        it.setPrintOp(value);
                        it = it.getNext();
                    }

                    it.setFunc(logF);
                    subEdit.setText(subEdit.getText() + "log");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_ln:
                    if((list.getLen() != 1) || (it.getPrintData() != 0))
                    {
                        value = MULT;
                        it.setPrintOp(value);

                        subEdit.setText(subEdit.getText() + opSet[value]);
                        edit.setText("");

                        list.addNode();
                        it.setPrintOp(value);
                        it = it.getNext();
                    }

                    it.setFunc(lnF);
                    subEdit.setText(subEdit.getText() + "ln");

                    isThere = false;
                    ifPass = false;
                    ifPoint = false;

                    break;

                case R.id.btn_deg:
                    list.setDeg(!list.getDeg());

                    if(list.getDeg()) deg.setText("Deg");
                    else deg.setText("Rad");

                    break;

            }
        }
    };

/*------------------------------------------------------------------------------------------------------------------------------*/

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