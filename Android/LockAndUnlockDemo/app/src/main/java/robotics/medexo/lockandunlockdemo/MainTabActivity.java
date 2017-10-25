package robotics.medexo.lockandunlockdemo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by makkit on 7/5/2017.
 */

public class MainTabActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

//        setContentView(R.layout.activity_main);
        addNewTab(MainActivity.class, "Scanner");
        addNewTab(LogActivity.class, "History");

        getTabHost().setCurrentTab(0);
        getTabHost().requestFocus();
    }

    public void addNewTab(Class<?> cls, String tabName){
        Intent intent = new Intent().setClass(this, cls);
        TabHost.TabSpec spec = getTabHost().newTabSpec(tabName)
                .setIndicator(tabName)
                .setContent(intent);
        getTabHost().addTab(spec);
    }

}
