package com.example.ex01calculadora;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText etDisp;
    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btSoma, btSubtracao, btMultiplicacao, btDivisao, btIgual, btLimpar;
    String valorDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etDisp = findViewById(R.id.edtDisplay);
        bt0 = findViewById(R.id.bt0);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);


        btSoma = findViewById(R.id.btSoma);
        btSubtracao = findViewById(R.id.btSubtracao);
        btMultiplicacao = findViewById(R.id.btMultiplicacao);
        btDivisao = findViewById(R.id.btDivisao);
        btIgual = findViewById(R.id.btIgual);

        btLimpar = findViewById(R.id.btLimpar);

        valorDisplay = etDisp.getText().toString();

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorDisplay = "0";
                etDisp.setText(valorDisplay);
            }
        });

        btIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(calcularResultado());
            }
        });

        btSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("+"));
            }
        });

        btSubtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("-"));
            }
        });

        btMultiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("*"));
            }
        });

        btDivisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("/"));
            }
        });

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("0"));
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("1"));
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("2"));
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("3"));
            }
        });


        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("4"));
            }
        });


        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("5"));
            }
        });


        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("6"));
            }
        });


        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("7"));
            }
        });


        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("8"));
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDisp.setText(atualizarValorDisplay("9"));
            }
        });
    }


    public String atualizarValorDisplay(String valor) {
        if (valorDisplay.equals("0")) {
            valorDisplay = valor;
        } else {
            valorDisplay += valor;
        }

        return valorDisplay;
    }

    public String calcularResultado() {
        String primeiroTermo = "", segundoTermo = "", operador = "";

        String equacao = valorDisplay;

        while (hasOperador(equacao)) {
            primeiroTermo = getTermo(equacao);
            operador = getOperador(equacao);
            String replace =primeiroTermo + operador;
            segundoTermo = getTermo(equacao.replace(replace, ""));

            Double resultado = runEquacao(primeiroTermo, segundoTermo, operador);
            equacao = atualizarEquacao(equacao, primeiroTermo, segundoTermo, operador, resultado);
        }

        valorDisplay = String.valueOf(equacao);
        return valorDisplay;
    }

    public boolean hasOperador(String equacao) {
        for (int i = 0; i < equacao.length(); i++) {
            char caractere = equacao.charAt(i);

            if (isOperador(caractere)) {
                return true;
            }
        }

        return false;
    }

    public String atualizarEquacao(String equacao, String primeiroTermo, String segundoTermo, String operador, Double resultado) {
        return equacao.replace(primeiroTermo + operador + segundoTermo, String.valueOf(resultado));
    }

    public String getTermo(String equacao) {
        String termo = "";

        for (int i = 0; i < equacao.length(); i++) {
            char caractere = equacao.charAt(i);

            if (isOperador(caractere)) {
                break;
            }

            termo = termo.concat(String.valueOf(caractere));

        }

        return termo;
    }

    public String getOperador(String equacao) {
        for (int i = 0; i < equacao.length(); i++) {
            char caractere = equacao.charAt(i);

            if (isOperador(caractere)) {
                return String.valueOf(caractere);
            }
        }

        return "";
    }

    public boolean isOperador(char valor) {
        switch (valor) {
            case '+':
                return true;
            case '-':
                return true;
            case '*':
                return true;
            case '/':
                return true;
            default:
                return false;
        }
    }

    public double runEquacao(String primeiroTermo, String segundoTermo, String operador) {
        if (operador.equals("+")) {
            return Double.parseDouble(primeiroTermo) + Double.parseDouble(segundoTermo);
        }

        if (operador.equals("-")) {
            return Double.parseDouble(primeiroTermo) - Double.parseDouble(segundoTermo);
        }

        if (operador.equals("*")) {
            return Double.parseDouble(primeiroTermo) * Double.parseDouble(segundoTermo);
        }

        return Double.parseDouble(primeiroTermo) / Double.parseDouble(segundoTermo);
    }
}






















