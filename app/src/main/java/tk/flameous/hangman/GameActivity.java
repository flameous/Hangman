package tk.flameous.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    
    TextView secretText;
    EditText input;
    ImageView imageView;
    char inputChar;

    int numOfChars;
    int openedChars = 0;
    String secretWord;
    char[] secretChars;
    int tries = 0;
    boolean isWin = false;
    char[] outputChars;

    String fuck = "";
    boolean isNotLoh = false;

    public static String endText = "loshara!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        secretText = (TextView)findViewById(R.id.textView);
        input = (EditText)findViewById(R.id.editText);
        imageView = (ImageView)findViewById(R.id.imageView);

        String[] wordsArray = getResources().getStringArray(R.array.goodArray);

        Random random = new Random();
        secretWord = wordsArray[random.nextInt(wordsArray.length)];
        numOfChars = secretWord.length();
        secretChars = new char[numOfChars];
        outputChars = new char[numOfChars];

        for (int i = 0; i < numOfChars; i++){
            secretChars[i] = secretWord.charAt(i);
            outputChars[i] = '_';
        }

        secretText.setText("-_-_-_-_-_-_-");
    }

    public void openChar(int a){
        openedChars++;
        outputChars[a] = secretChars[a];

        if (openedChars == numOfChars){
            isWin = true;
            endText = "Красава, братуха!";
        }
    }

    public void kostyl(int b){
        switch (b){
            case 1: imageView.setImageResource(R.drawable.pic2);
                break;
            case 2:imageView.setImageResource(R.drawable.pic3);
                break;
            case 3:imageView.setImageResource(R.drawable.pic4);
                break;
            case 4:imageView.setImageResource(R.drawable.pic5);
                break;
            case 5:imageView.setImageResource(R.drawable.pic6);
                break;
            default:break;
        }
    }

    public void openClick(View view){
        inputChar = input.getText().charAt(0);

        for (int i = 0; i < numOfChars; i++) {
            if (inputChar == secretChars[i]){
                openChar(i);
                isNotLoh = true;
            }
            fuck += outputChars[i];
        }

        if (!(isNotLoh)){
            tries++;
            kostyl(tries);
        }
        isNotLoh = false;

        secretText.setText(fuck);
        fuck = "";

        if ((tries == 5)||(isWin)){
            Intent intent = new Intent(GameActivity.this, EndActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void showAll(View view){
        secretText.setText(secretWord);
    }

}
