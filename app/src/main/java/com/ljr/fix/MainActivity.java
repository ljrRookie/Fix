package com.ljr.fix;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.result);
        // Example of a call to a native method


    }


    public void caculator(View view) {
        Caclutor caclutor = new Caclutor();
        mTextView.setText("  "+caclutor.caculator());
        Caclutor caclutor1 = new Caclutor();
        mTextView.setText("  "+caclutor1.caculator());
        Caclutor caclutor2 = new Caclutor();

        mTextView.setText("  "+caclutor2.caculator());
    }

    public void fix(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "fix.dex");
        DexManager dexManager = new DexManager(this);
        dexManager.load(file);
    }
}
