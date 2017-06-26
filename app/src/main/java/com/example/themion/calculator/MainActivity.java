package com.example.themion.calculator;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import java.lang.*;

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

/*------------------------------------------------------------------------------------------------------------------------------*/

    final int noF = 0;
    final int sinF = 1;
    final int cosF = 2;
    final int tanF = 3;
    final int logF = 4;
    final int lnF = 5;
    final int absF = 6;

/*------------------------------------------------------------------------------------------------------------------------------*/

    TextView edit, subEdit;

    Button C, back, hist;
    Button add, sub, mult, div, pow, equ;
    Button brac, abs;
    Button log, ln, squ;
    Button sin, cos, tan, deg;

    boolean isThere = false;
    boolean ifPass = false;

    String histList = "";
    Intent intent;

    private CalculateList list;
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
        pow = (Button) findViewById(R.id.btn_pow);
        equ = (Button) findViewById(R.id.btn_equ);

        add.setOnClickListener(opLsn);
        sub.setOnClickListener(opLsn);
        mult.setOnClickListener(opLsn);
        div.setOnClickListener(opLsn);
        pow.setOnClickListener(opLsn);
        equ.setOnClickListener(opLsn);

 /*------------------------------------------------------------------------------------------------------------------------------*/

        brac = (Button) findViewById(R.id.btn_brac);
        brac.setOnClickListener(cLsn);

        abs = (Button) findViewById(R.id.btn_abs);
        squ = (Button) findViewById(R.id.btn_squ);
        log = (Button) findViewById(R.id.btn_log);
        ln = (Button) findViewById(R.id.btn_ln);

        abs.setOnClickListener(fLsn);
        squ.setOnClickListener(fLsn);
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

        hist = (Button) findViewById(R.id.btn_hist);
        C = (Button) findViewById(R.id.btn_C);
        back = (Button) findViewById(R.id.btn_back);

        hist.setOnClickListener(sLsn);
        C.setOnClickListener(sLsn);
        back.setOnClickListener(sLsn);

        list = new CalculateList();
        it = list.getLast();

        subEdit.setText("");
        edit.setText("");

        intent = new Intent(this, HistActivity.class);
    }

/*------------------------------------------------------------------------------------------------------------------------------*/

    public void onClick(View v)
    {
        CalculateList abc;

        if(it.getNext() != null) it = it.getNext();

        if (isThere)
        {
            edit.setText("");
            subEdit.setText("");

            list = new CalculateList();
            it = list.getLast();

            isThere = false;
            ifPass = false;
        }

        if(ifPass)
        {
            it.setPrintOp(MULT);

            list.addNode();
            it = it.getNext();

            abc = list;
            while(abc.getMother() != null) abc = abc.getMother().getMotherList();

            subEdit.setText(abc.print());

            ifPass = false;
        }

        if(it.getPoint() > 0) it.setPoint(it.getPoint() + 1);

        switch (v.getId())
        {
            case R.id.btn_help:
                Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mathway.com/ko/FiniteMath"));
                link.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(link);

                break;

            case R.id.btn_0:
                it.setPrintData(it.getPrintData() * 10 + 0);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_1:
                it.setPrintData(it.getPrintData() * 10 + 1);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_2:
                it.setPrintData(it.getPrintData() * 10 + 2);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_3:
                it.setPrintData(it.getPrintData() * 10 + 3);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_4:
                it.setPrintData(it.getPrintData() * 10 + 4);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_5:
                it.setPrintData(it.getPrintData() * 10 + 5);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_6:
                it.setPrintData(it.getPrintData() * 10 + 6);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_7:
                it.setPrintData(it.getPrintData() * 10 + 7);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_8:
                it.setPrintData(it.getPrintData() * 10 + 8);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_9:
                it.setPrintData(it.getPrintData() * 10 + 9);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_point:
                if(!it.getHit()) break;
                if(it.getPoint() == 0) it.setPoint(1);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                break;

            case R.id.btn_pi:
                if(it.getHit())
                {
                    it.setPrintOp(MULT);
                    list.addNode();
                    it = it.getNext();
                }

                it.setPrintData(Math.PI);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                isThere = false;
                ifPass = true;

                break;

            //자연상수 e를 입력할 때
            case R.id.btn_exp:
                if(it.getHit())
                {
                    it.setPrintOp(MULT);
                    list.addNode();
                    it = it.getNext();
                }

                it.setPrintData(Math.E);
                it.setHit(true);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                isThere = false;
                ifPass = true;

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

            if(it.getPoint() == 1) return;

            CalculateList abc;

            if (isThere)
            {
                double data = Double.parseDouble(edit.getText().toString());

                edit.setText("");

                list = new CalculateList();
                it = list.getLast();
                it.setHit(true);

                if(data == (int)data) it.setPoint(0);
                else
                {
                    it.setPoint(2);
                    data *= 10;
                }

                it.setPrintData(data);

                abc = list;

                subEdit.setText(abc.print());

                isThere = false;
                ifPass = false;
            }

            switch (v.getId()) {
                case R.id.btn_add:
                    if(!it.getHit()) return;

                    it.setPrintOp(ADD);

                    list.addNode();
                    it = it.getNext();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_sub:
                    if(!it.getHit())
                    {
                        if(!it.getPM())
                        {
                            if(list.getMother() == null)
                            {
                                it.setBracList(new CalculateList());
                                it.getBracList().setMother(it);
                                it.setMotherList(list);

                                list = it.getBracList();
                                it = it.getBracList().getFirst();
                            }

                            it.setPM(true);
                        }

                        else
                        {
                            if(list.getMother().getFunc() == noF)
                            {
                                it = list.getMother();
                                list = it.getMotherList();

                                it.setBracList(null);
                            }

                            it.setPM(false);
                        }

                        abc = list;
                        while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                        subEdit.setText(abc.print());
                        break;
                    }

                    it.setPrintOp(SUB);
                    it.setCalcOp(ADD);

                    list.addNode();
                    it = it.getNext();
                    it.setPM(true);

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_mult:
                    if(!it.getHit()) return;

                    it.setPrintOp(MULT);

                    list.addNode();
                    it = it.getNext();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_div:
                    if(!it.getHit()) return;

                    it.setPrintOp(DIV);
                    it.setCalcOp(MULT);

                    list.addNode();
                    it = it.getNext();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_pow:
                    if(!it.getHit()) return;

                    it.setPrintOp(POW);

                    list.addNode();
                    it = it.getNext();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_equ:
                    if(!it.getHit()) return;

                    while (list.getMother() != null)
                    {
                        it.setPrintOp(EQU);
                        it = list.getMother();
                        it.setPrintData(it.getBracList().doCalc());
                        it.setHit(true);

                        list = it.getMotherList();
                    }

                    it.setPrintOp(EQU);

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();
                    String txt = abc.print();

                    subEdit.setText(txt);

                    double prt = list.doCalc();

                    if (prt == (int) prt)
                    {
                        edit.setText(((int) prt) + "");
                        histList += txt + opSet[EQU] + (int) prt + "\n";
                    }
                    else
                    {
                        edit.setText(prt + "");
                        histList += txt + opSet[EQU] + prt + "\n";
                    }

                    isThere = true;
                    ifPass = false;

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
            if(it.getPoint() == 1) return;

            CalculateList abc;

            if (isThere)
            {
                double data = Double.parseDouble(edit.getText().toString());

                edit.setText("");

                list = new CalculateList();
                it = list.getLast();
                it.setHit(true);
                it.setPrintData(data);

                if(data == (int)data) it.setPoint(0);
                else it.setPoint(1);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                isThere = false;
                ifPass = false;
            }

            switch(v.getId())
            {
                case R.id.btn_brac:
                    if((list.getMother() != null) && (it.getHit()))
                    {
                        it.setPrintOp(EQU);
                        it = list.getMother();
                        it.setHit(true);
                        it.setPrintData(list.doCalc());

                        list = it.getMotherList();

                        isThere = false;
                        ifPass = true;
                    }

                    else
                    {
                        if(it.getHit())
                        {
                            subEdit.setText(subEdit.getText() + opSet[MULT]);

                            it.setPrintOp(MULT);
                            list.addNode();
                            it = it.getNext();
                        }

                        it.setBracList(new CalculateList());
                        it.getBracList().setMother(it);
                        it.setMotherList(list);

                        list = it.getBracList();
                        it = it.getBracList().getFirst();

                        isThere = false;
                        ifPass = false;
                    }

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

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
            if(it.getPoint() == 1) return;

            CalculateList abc;

            if (isThere)
            {
                double data = Double.parseDouble(edit.getText().toString());

                edit.setText("");

                list = new CalculateList();
                it = list.getLast();
                it.setHit(true);
                it.setPrintData(data);

                if(data == (int)data) it.setPoint(0);
                else it.setPoint(1);

                abc = list;
                while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                subEdit.setText(abc.print());

                isThere = false;
                ifPass = false;
            }

            switch(v.getId())
            {
                case R.id.btn_sin:
                    if(it.getHit())
                    {
                        it.setPrintOp(MULT);
                        list.addNode();
                        it = it.getNext();
                    }

                    it.setFunc(sinF);

                    it.setBracList(new CalculateList());
                    it.getBracList().setMother(it);
                    it.setMotherList(list);

                    list = it.getBracList();
                    it = it.getBracList().getFirst();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_cos:
                    if(it.getHit())
                    {
                        it.setPrintOp(MULT);
                        list.addNode();
                        it = it.getNext();
                    }

                    it.setFunc(cosF);
                    it.setBracList(new CalculateList());
                    it.getBracList().setMother(it);
                    it.setMotherList(list);

                    list = it.getBracList();
                    it = it.getBracList().getFirst();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_tan:
                    if(it.getHit())
                    {
                        it.setPrintOp(MULT);
                        list.addNode();
                        it = it.getNext();
                    }

                    it.setFunc(tanF);
                    it.setBracList(new CalculateList());
                    it.getBracList().setMother(it);
                    it.setMotherList(list);

                    list = it.getBracList();
                    it = it.getBracList().getFirst();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_squ:
                    if(!it.getHit()) break;

                    it.setPrintOp(POW);

                    list.addNode();
                    it = it.getNext();
                    it.setPrintData(2);
                    it.setHit(true);

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = true;

                    break;

                case R.id.btn_log:
                    if(it.getHit())
                    {
                        it.setPrintOp(MULT);
                        list.addNode();
                        it = it.getNext();
                    }

                    it.setFunc(logF);
                    it.setBracList(new CalculateList());
                    it.getBracList().setMother(it);
                    it.setMotherList(list);

                    list = it.getBracList();
                    it = it.getBracList().getFirst();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_ln:
                    if(it.getHit())
                    {
                        it.setPrintOp(MULT);
                        list.addNode();
                        it = it.getNext();
                    }

                    it.setFunc(lnF);
                    it.setBracList(new CalculateList());
                    it.getBracList().setMother(it);
                    it.setMotherList(list);

                    list = it.getBracList();
                    it = it.getBracList().getFirst();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_abs:
                    if(it.getHit())
                    {
                        it.setPrintOp(MULT);
                        list.addNode();
                        it = it.getNext();
                    }

                    it.setFunc(absF);
                    it.setBracList(new CalculateList());
                    it.getBracList().setMother(it);
                    it.setMotherList(list);

                    list = it.getBracList();
                    it = it.getBracList().getFirst();

                    abc = list;
                    while(abc.getMother() != null) abc = abc.getMother().getMotherList();

                    subEdit.setText(abc.print());

                    isThere = false;
                    ifPass = false;

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

    Button.OnClickListener sLsn = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btn_hist:
                    intent.putExtra("History", histList);
                    startActivity(intent);

                    break;

                case R.id.btn_C:
                    edit.setText("");
                    subEdit.setText("");
                    deg.setText("Rad");

                    list = new CalculateList();
                    list.setDeg(false);
                    it = list.getLast();

                    isThere = false;
                    ifPass = false;

                    break;

                case R.id.btn_back:
                    if(it.getPrintOp() == EQU)
                    {
                        it.setPrintData(-1);
                        isThere = false;
                    }

                    if(it.getBracList() != null)
                    {
                        it.setPrintData(0);
                        it.setHit(false);

                        list = it.getBracList();
                        it = list.getLast();
                        it.setPrintOp(NOT_AN_OPERATOR);
                    }

                    else if(it.getHit())
                    {
                        if((it.getPrintData() == Math.PI) || (it.getPrintData() == Math.E))
                        {
                            it.setPrintData(0);
                            it.setHit(false);
                            ifPass = false;
                        }

                        else if(it.getPoint() == 1) it.setPoint(0);

                        else
                        {
                            if((it.getPrintData() < 10) && (it.getPoint() == 0)) it.setHit(false);
                            else if(it.getPoint() > 0) it.setPoint(it.getPoint() - 1);

                            it.setPrintData((int) (it.getPrintData() / 10));
                        }
                    }

                    else if((!it.getHit()) && (it == list.getFirst()) && (list.getMother() != null))
                    {
                        it = list.getMother();
                        list = it.getMotherList();
                        it.setBracList(null);

                        if(it.getFunc() != noF)  it.setFunc(noF);

                        isThere = false;
                        ifPass = false;
                    }

                    else if((it.getPrev() != null) && (it.getPrev().getPrintOp() != NOT_AN_OPERATOR))
                    {
                        it.getPrev().setPrintOp(NOT_AN_OPERATOR);
                        list.popNode();
                        it = list.getLast();
                    }

                    CalculateList abcd = list;
                    while(abcd.getMother() != null) abcd = abcd.getMother().getMotherList();
                    String text = abcd.print();

                    subEdit.setText(text);
                    edit.setText("");

                    break;
            }
        }
    };
}