package quiz.olgamrost.com.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStream;

import static javax.xml.transform.OutputKeys.ENCODING;

public class ShowHelp extends Activity {
    private static final String MIMETYPE = "text/html";
    private static final String ENCODING = "UTF-8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.helpview );

        final WebView view = (WebView) findViewById( R.id.helpview );
        view.getSettings().setJavaScriptEnabled( true );



        InputStream is = getResources().openRawResource( R.raw.help );

        try {
            if (( is != null ) && is.available() > 0) {
                final byte[] bytes = new byte[is.available()];
                is.read( bytes );
                view.loadDataWithBaseURL( null, new String( bytes ), MIMETYPE,
                        ENCODING, null );

            }
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }

        view.bringToFront();
    }


    protected void goBack(View v){

        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
