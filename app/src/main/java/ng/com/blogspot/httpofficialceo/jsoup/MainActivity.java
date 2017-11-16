package ng.com.blogspot.httpofficialceo.jsoup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.texts);
        loadButton = (Button) findViewById(R.id.load_button);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              
                new getInfo().execute();

            }
        });

    }

    public class getInfo extends AsyncTask<Void, Void, Void>{


        String words;

        @Override
        protected Void doInBackground(Void... params) {


            try {
                Document docs = Jsoup.connect("http://edition.cnn.com/").get();

                words = docs.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            text.setText(words);

        }
    }
}
