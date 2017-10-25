package robotics.medexo.lockandunlockdemo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by makkit on 11/5/2017.
 */

public class LogTabActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

//        setContentView(R.layout.activity_main);
        addNewTab(LogActivity.class, "Daily");
        addNewTab(LogActivity.class, "Weekly");
        addNewTab(LogActivity.class, "Drug Log");

        getTabHost().setCurrentTab(0);
        getTabHost().requestFocus();
    }

    public void addNewTab(Class<?> cls, String tabName){
        Intent intent = new Intent().setClass(this, cls);
        TabHost.TabSpec spec = getTabHost().newTabSpec(tabName)
                .setIndicator(tabName)
                .setContent(intent);
        if(tabName.equals("Daily")){
            intent.putExtra("displayImage", "@drawable/daily");
        }else if(tabName.equals("Weekly")){
            intent.putExtra("displayImage", "@drawable/weekly");
        }else if(tabName.equals("Drug Log")){
            intent.putExtra("displayImage", "@drawable/drugs");
        }

        getTabHost().addTab(spec);
    }
}
