package com.example.ankita.scarnesdice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

   public int myscore = 0, compscore = 0, cur_myscore = 0, cur_compscore = 0, r;
    boolean yturn = true;
    ImageView dface;
    TextView turntext;
    TextView ms;
    TextView cs;
    TextView cms;
    TextView ccs;
    Random random;
    Button holdButton;
    Button resetButton;
    Button rollButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dface = (ImageView) findViewById(R.id.image);
        ms = (TextView) findViewById(R.id.yt);
        cs = (TextView) findViewById(R.id.ct);
        cms = (TextView) findViewById(R.id.yc);
        ccs = (TextView) findViewById(R.id.cc);
        rollButton = (Button) findViewById(R.id.rollbutton);
        resetButton = (Button) findViewById(R.id.resetbutton);
        holdButton = (Button) findViewById(R.id.holdbutton);
        turntext = (TextView) findViewById(R.id.turn);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void df(int num)

    {
        switch (num) {
            case 1:
                dface.setImageResource(R.drawable.dice1);
                break;
            case 2:
                dface.setImageResource(R.drawable.dice2);
                break;
            case 3:
                dface.setImageResource(R.drawable.dice3);
                break;
            case 4:
                dface.setImageResource(R.drawable.dice4);
                break;
            case 5:
                dface.setImageResource(R.drawable.dice5);
                break;
            case 6:
                dface.setImageResource(R.drawable.dice6);
                break;
            default:
                dface.setImageResource(R.drawable.dice1);
                break;
        }
    }

    public void change() {
        if (yturn) {
            yturn = false;
            turntext.setText("COMPUTERS TURN");
        } else {
            yturn = true;
            turntext.setText("YOUR TURN");
        }
        compscore += cur_compscore;
        myscore += cur_myscore;
        cur_myscore = 0;
        cur_compscore = 0;
        ccs.setText("" + cur_compscore);
        cms.setText("" + cur_myscore);
        cs.setText("" + compscore);
        ms.setText("" + myscore);

    }

    public void hold(View view) {

        if (yturn) {
            yturn = false;
            turntext.setText("COMPUTERS TURN");
        } else {
            yturn = true;
            turntext.setText("YOUR TURN");
        }
        compscore += cur_compscore;
        myscore += cur_myscore;
        cur_myscore = 0;
        cur_compscore = 0;

        ccs.setText("" + cur_compscore);
        cms.setText("" + cur_myscore);
        cs.setText("" + compscore);
        ms.setText("" + myscore);

        if(myscore>=100||compscore>=100)
        {
            Context context = getApplicationContext();
            if(myscore>=100)
                Toast.makeText(context,"You Won !!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context,"You Lost !!", Toast.LENGTH_SHORT).show();

            reset(view);
        }
    }


    public void roll(View view) {
        random = new Random();
        r = random.nextInt(6) + 1;
        df(r);
        if (yturn) {
            if (r != 1)
                cur_myscore += r;
            else {
                cur_myscore = 0;
                change();

            }

        } else {
            if (r != 1)
                cur_compscore += r;
            else {
                cur_compscore = 0;
                change();
            }
        }
        ccs.setText("" + cur_compscore);
        cms.setText("" + cur_myscore);
    }


    public void reset(View view) {
        myscore = 0;
        compscore = 0;
        cur_myscore = 0;
        cur_compscore = 0;
        ccs.setText("" + cur_compscore);
        cms.setText("" + cur_myscore);
        cs.setText("" + compscore);
        ms.setText("" + myscore);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ankita.scarnesdice/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ankita.scarnesdice/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
