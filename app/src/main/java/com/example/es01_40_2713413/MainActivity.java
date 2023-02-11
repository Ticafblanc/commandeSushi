/*

Nom et Prenom: DoQuocBao Matthis

Matricule: 2713413

Groupe: 040

Examen: E501

date: 09/02/2023

 */

package com.example.es01_40_2713413;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final double PrixSushi = 6.75;
    final double PrixTemari = 7.50;
    final double PrixTofu = 9.80;
    int Sushi = 0, Temari = 1, Tofu = 2;
    int[] Nbr = new int[3];
    String[] Str = new String[3];
    EditText[] Edit = new EditText[3];
    TextView ViewReponse;

    /*mets a jours les données text avant manipulation*/
    public void MyfindViewById(){
        Edit[Sushi] = findViewById(R.id.NumberSushi);
        Edit[Temari] = findViewById(R.id.NumberTamari);
        Edit[Tofu] = findViewById(R.id.NumberTofus);
        ViewReponse = findViewById(R.id.Reponse);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ViewReponse = findViewById(R.id.Reponse);

        /*recuper les données du bundle*/
        CharSequence RepSave= savedInstanceState.getCharSequence("SaveReponse");
        int ColSave= savedInstanceState.getInt("SaveColor");


        /*et le set dans le widget*/
        ViewReponse.setTextColor(ColSave);
        ViewReponse.setText(RepSave);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        ViewReponse = findViewById(R.id.Reponse);

        /*recuper le widget text a sauvgarder*/
        CharSequence RepSave = ViewReponse.getText();
        ColorStateList ColSave = ViewReponse.getTextColors();

        /*et le sauvgarde dans le bundle*/
        savedInstanceState.putCharSequence("SaveReponse", RepSave);
        savedInstanceState.putInt("SaveColor", ColSave.getDefaultColor());
    }

    @SuppressLint("ResourceAsColor")
    public void FunctEffacer(View view){
        /* effacer les entree si on appuie sur le button entree*/
        MyfindViewById();
        Edit[Sushi].setText("");
        Edit[Temari].setText("");
        Edit[Tofu].setText("");
        ViewReponse.setText("");
    }

    public boolean CheckValeur() {
        boolean Trigger = false;

        /*manage les erreur de d'entree et converti les entree en double*/
        Nbr[Sushi] = 0;
        Nbr[Tofu] = 0;
        Nbr[Temari] = 0;
        if (!Str[Sushi].isEmpty() && Str[Sushi].length() < 5) {
            Nbr[Sushi] = Integer.parseInt(Str[Sushi]);
            Trigger = true;
        }
        if (!Str[Temari].isEmpty() && Str[Temari].length() < 5) {
            Nbr[Temari] = Integer.parseInt(Str[Temari]);
            Trigger = true;
        }
        if (!Str[Tofu].isEmpty() && Str[Tofu].length() < 5) {
            Nbr[Tofu] = Integer.parseInt(Str[Tofu]);
            Trigger = true;
        }
        if ( Nbr[Sushi] < 0 || Nbr[Temari] < 0 || Nbr[Tofu] < 0){
            ViewReponse.setTextColor(getResources().getColor(R.color.red));
            ViewReponse.setTextSize(12);
            ViewReponse.setText(R.string.error_negatif);
            return false;
        }
        if ( Nbr[Sushi] > 1000 || Str[Sushi].length() > 4) {
            ViewReponse.setTextColor(getResources().getColor(R.color.red));
            ViewReponse.setTextSize(12);
            ViewReponse.setText(R.string.error_trop_sushi);
            return false;
        }
        if ( Nbr[Temari] > 1000 || Str[Temari].length() > 4) {
            ViewReponse.setTextColor(getResources().getColor(R.color.red));
            ViewReponse.setTextSize(12);
            ViewReponse.setText(R.string.error_trop_tamari);
            return false;
        }
        if ( Nbr[Tofu] > 1000 || Str[Tofu].length() > 4) {
            ViewReponse.setTextColor(getResources().getColor(R.color.red));
            ViewReponse.setTextSize(12);
            ViewReponse.setText(R.string.error_trop_tofu);
            return false;
        }
        if ( !Trigger ){
            ViewReponse.setTextColor(getResources().getColor(R.color.red));
            ViewReponse.setText(R.string.error_vide);
            return false;
        }
        return Trigger;
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n", "DefaultLocale"})
    public void FunctTotal(View view) {
        /*lit les entree te manage la sortie lors de boutton total*/
        MyfindViewById();
        Str[Sushi] = Edit[Sushi].getText().toString();
        Str[Temari] = Edit[Temari].getText().toString();
        Str[Tofu] = Edit[Tofu].getText().toString();
        if (CheckValeur()) {
            double Total = Nbr[Sushi] * PrixSushi + Nbr[Temari] * PrixTemari + Nbr[Tofu] * PrixTofu;
            ViewReponse.setTextColor(getResources().getColor(R.color.black));
            ViewReponse.setText(String.format("%.2f", Total) + " Dollars");
        }
    }
}