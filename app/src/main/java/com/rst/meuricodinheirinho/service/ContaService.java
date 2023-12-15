package com.rst.meuricodinheirinho.service;

import android.content.Context;
import android.os.Environment;

import com.rst.meuricodinheirinho.model.Conta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ContaService {

    public List<Conta> carregarListaContas(Context context) {

        List<Conta> contas = recuperaListaContasArquivo(context);
        if(contas==null){
            contas= new ArrayList<>();
            Conta c = criarNovaConta("Conta corrente");
            contas.add(c);
        }
        return contas;
    }

    public Conta criarNovaConta(String nome) {
        Conta novaConta = new Conta(nome);
        return novaConta;
    }

    public void armazenaListaContas(Context context, List<Conta> contas) {

        try {
            //you may also write this verbosely as
            // FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            FileOutputStream fileOutputStream = context.openFileOutput("bd.dat", Context.MODE_PRIVATE);

            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(contas);
            //we don't want a memory leak if we can avoid it
            fileOutputStream.close();
            objOutputStream.close();

        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Conta> recuperaListaContasArquivo(Context context){
        List<Conta> contas=null;
        try {
            //could be written as
            // FileInputStream fileInputStream = new
            //FileInputStream(fileName);
            FileInputStream fileInputStream = context.openFileInput("bd.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            //read the binary file
           contas = (List<Conta>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return contas;
    }

}
