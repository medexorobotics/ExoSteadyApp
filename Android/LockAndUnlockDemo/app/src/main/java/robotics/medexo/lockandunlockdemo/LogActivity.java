package robotics.medexo.lockandunlockdemo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by makkit on 7/5/2017.
 */

public class LogActivity extends Activity {
    private ImageView displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.log_layout);
        setContentView(R.layout.demo_tab_layout);

//        logList = (ListView) findViewById(R.id.list);
        Bundle bundle = getIntent().getExtras();
        String image = bundle.getString("displayImage");

        int imageResource = getResources().getIdentifier(image, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);

        displayImage = (ImageView) findViewById(R.id.imageContent);
        displayImage.setImageDrawable(res);

    }

}
