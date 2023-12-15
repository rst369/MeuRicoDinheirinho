package com.rst.meuricodinheirinho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.ui.AppBarConfiguration;

import com.rst.meuricodinheirinho.databinding.ActivityMainBinding;

import com.rst.meuricodinheirinho.databinding.ContaCorrenteBinding;
import com.rst.meuricodinheirinho.model.Conta;
import com.rst.meuricodinheirinho.service.ContaService;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ESPACO_CONTAS = 250;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private List<Conta> contas;

    private ContaService contaService = new ContaService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.contas = contaService.carregarListaContas(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mostrarContas();

        findViewById(R.id.buttonNovaConta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCriarConta();
            }
        });

        findViewById(R.id.buttonSair).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contaService.armazenaListaContas(this,this.contas);
    }



    private void dialogCriarConta() {
        String text;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insira o nome da Conta");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            String text2;
            @Override
            public void onClick(DialogInterface dialog, int which) {
               String nome = input.getText().toString();
               adicionarConta(contaService.criarNovaConta(nome));
               mostrarContas();
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void adicionarConta(Conta c){
        this.contas.add(c);
    }

    private void mostrarContas() {
        final ConstraintLayout layout = findViewById(R.id.coordinatorLayout);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        int linha=-ESPACO_CONTAS;
        for(Conta c:this.contas){

//            TextView nomeConta =  new TextView(this);
//            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams
//                    ((int) CoordinatorLayout.LayoutParams.WRAP_CONTENT,(int) CoordinatorLayout.LayoutParams.WRAP_CONTENT);
//            params.leftMargin = 50;
//            params.topMargin  = linha+100;
//            nomeConta.setText(c.getNome());
//            nomeConta.setTextSize((float) 20);
//            nomeConta.setPadding(20, 50, 20, 50);
//            nomeConta.setLayoutParams(params);
//
//            relativeLayout.addView(nomeConta);
//
//
//            TextView saldoConta =  new TextView(this);
//            params=new RelativeLayout.LayoutParams
//                    ((int) CoordinatorLayout.LayoutParams.WRAP_CONTENT,(int) CoordinatorLayout.LayoutParams.WRAP_CONTENT);
//            params.leftMargin = 50;
//            params.topMargin  = linha+150;
//            saldoConta.setText("Saldo: "+c.getSaldo());
//            saldoConta.setTextSize((float) 20);
//            saldoConta.setPadding(20, 50, 20, 50);
//            saldoConta.setLayoutParams(params);
//
//            relativeLayout.addView(saldoConta);
//
//            linha=linha+100;

//            DadosContaBinding binding = DataBindingUtil.setContentView(this, R.layout.conta_component);
            ContaCorrenteBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.conta_corrente, relativeLayout, true);
            binding.setConta(c);

            ViewGroup parent = (ViewGroup) binding.coordinatorLayout.getParent();
            if(parent!=null){
                parent.removeView(binding.coordinatorLayout);
            }

            linha= linha+ESPACO_CONTAS;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.coordinatorLayout.getLayoutParams();
            params.setMargins(0,linha,0,0);
            binding.coordinatorLayout.setLayoutParams(params);

            relativeLayout.addView(binding.coordinatorLayout);
        }
        layout.addView(relativeLayout);
    }


}