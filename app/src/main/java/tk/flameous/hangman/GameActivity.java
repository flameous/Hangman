package tk.flameous.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    
    TextView secretText;
    EditText input;
    ImageView imageView;
    char inputChar;

    int numOfChars;
    int openedChars = 0;
    public static String secretWord;
    char[] secretChars;
    int tries = 0;
    public static boolean isWin;
    char[] outputChars;

    String text = "";
    boolean isNotLoh = false;

    boolean[] boolChars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        isWin = false;
        secretText = (TextView)findViewById(R.id.textView);
        input = (EditText)findViewById(R.id.editText);
        imageView = (ImageView)findViewById(R.id.imageView);

        String[] wordsArray = getResources().getStringArray(R.array.goodArray);

        Random random = new Random();
        int randomWord = random.nextInt(wordsArray.length);
        String[] strings = wordsArray[randomWord].split(" ");

        secretWord = strings[0];
        String helpWord = strings[1];
        numOfChars = secretWord.length();
        secretChars = new char[numOfChars];
        outputChars = new char[numOfChars];
        boolChars = new boolean[numOfChars];


        for (int i = 0; i < numOfChars; i++){
            secretChars[i] = secretWord.charAt(i);
            outputChars[i] = '_';
        }

        secretText.setText(helpWord + " (" + numOfChars + ")");
    }

    public void openChar(int a){
        openedChars++;
        outputChars[a] = secretChars[a];
        boolChars[a] = true;

        if (openedChars == numOfChars){
            isWin = true;
        }
    }

    public void kostyl(int b){
        switch (b){
            case 1:imageView.setImageResource(R.drawable.pic2);
                break;
            case 2:imageView.setImageResource(R.drawable.pic3);
                break;
            case 3:imageView.setImageResource(R.drawable.pic4);
                break;
            case 4:imageView.setImageResource(R.drawable.pic5);
                break;
            case 5:imageView.setImageResource(R.drawable.pic6);
                break;
            case 6:imageView.setImageResource(R.drawable.pic7);
            default:
                break;
        }
    }

    public void openClick(View view){
        String text = input.getText().toString().toLowerCase();
        input.setText("");
        if (text.length() == 1) {
            inputChar = text.charAt(0);
        }
        else {
            Toast.makeText(GameActivity.this, "Enter the char, please", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < numOfChars; i++) {
            if ( (!(boolChars[i])) && (inputChar == secretChars[i]) ){
                openChar(i);
                isNotLoh = true;
            }
            this.text += outputChars[i];
        }

        if (!(isNotLoh)){
            tries++;
            kostyl(tries);
        }
        isNotLoh = false;

        secretText.setText(this.text);
        this.text = "";

        if ((tries > 5)||(isWin)){
            Intent intent = new Intent(GameActivity.this, EndActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void showAll(View view){
        secretText.setText(secretWord);
    }

}
