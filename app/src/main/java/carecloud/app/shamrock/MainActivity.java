package carecloud.app.shamrock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {

            // create the fragment and pass the lang options and the title
            SelectLanguageFragment fragment = new SelectLanguageFragment();


            // add select language fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_holder, fragment, null)
                    .commit();
        }
    }



}
