package tk.flameous.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        ImageView imageView = (ImageView)findViewById(R.id.imageView3);
        TextView textView = (TextView)findViewById(R.id.textView2);

        String text;
        if (GameActivity.isWin) {
            text = "You win!";
            imageView.setImageResource(R.drawable.coolcat);
        }
        else{
            text = "You lose!";
        }
        textView.setText(text + "\n\n" + GameActivity.secretWord);
    }
}
