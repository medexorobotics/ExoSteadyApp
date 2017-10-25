package robotics.medexo.lockandunlockdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.os.Vibrator;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity  extends BlunoLibrary {
	private Button buttonLock;
    private Button buttonUnlock;
    private Button buttonScan;
    private Button buttonToggleMode;

    private ImageView lockImage;

	private LineChart mChart;
    private ILineDataSet[] mSets;
    private Boolean mInit = false;
    private Integer mOverCount;
    private ArrayList<String> mEventLog;

    private static int SAMPLE_SIZE = 11;
    private static final int[] COLORFUL_COLORS = {
            Color.rgb(193, 37, 82), Color.rgb(5, 102, 250), Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31), Color.rgb(179, 100, 53), Color.rgb(240, 240, 240)
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        onCreateProcess();														//onCreate Process by BlunoLibrary


        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200

        lockImage = (ImageView) findViewById(R.id.imageView3);

        buttonScan = (Button) findViewById(R.id.buttonScan);					//initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				buttonScanOnClickProcess();										//Alert Dialog for selecting the BLE device
			}
		});

        buttonLock = (Button) findViewById(R.id.buttonLock);
        buttonLock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lockImage.setImageResource(R.drawable.locked);
                sendLockSignal();
            }
        });

        buttonUnlock = (Button) findViewById(R.id.buttonUnlock);
        buttonUnlock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lockImage.setImageResource(R.drawable.unlocked);
                sendUnlockSignal();
            }
        });

        buttonToggleMode = (Button) findViewById(R.id.buttonToggleMode);
        buttonToggleMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToggleMode();
            }
        });
	}

	protected void onResume(){
		super.onResume();
		System.out.println("BlUNOActivity onResume");
		onResumeProcess();														//onResume Process by BlunoLibrary
	}

    private void sendLockSignal(){
        String signal = "c";

        System.out.println("Try to send this to Bluno: "+ signal);
        serialSend(signal);
    }

    private void sendUnlockSignal(){
        String signal = "o";

        System.out.println("Try to send this to Bluno: "+ signal);
        serialSend(signal);
    }

    private void sendToggleMode(){
        String text = buttonToggleMode.getText().toString();
        String signal = "m";

        if(text.equals("切換至自動模式")){
            signal = "a";
            buttonToggleMode.setText("切換至手動模式");
            buttonLock.setVisibility(View.GONE);
            buttonUnlock.setVisibility(View.GONE);
        }else{
            buttonToggleMode.setText("切換至自動模式");
            buttonLock.setVisibility(View.VISIBLE);
            buttonUnlock.setVisibility(View.VISIBLE);
        }

        System.out.println("Try to send this to Bluno: "+ signal);
        serialSend(signal);

    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
		super.onActivityResult(requestCode, resultCode, data);
	}
	
    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();														//onPause Process by BlunoLibrary
    }
	
	protected void onStop() {
		super.onStop();
		onStopProcess();														//onStop Process by BlunoLibrary
	}
    
	@Override
    protected void onDestroy() {
        super.onDestroy();	
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

	@Override
	public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
		switch (theConnectionState) {											//Four connection state
		case isConnected:
			buttonScan.setText("Linked");
			break;
		case isConnecting:
			buttonScan.setText("Linking");
			break;
		case isToScan:
			buttonScan.setText("Link up");
			break;
		case isScanning:
			buttonScan.setText("Scanning");
			break;
		case isDisconnecting:
			buttonScan.setText("isDisconnecting");
			break;
		default:
			break;
		}
	}

	@Override
	public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
		// TODO Auto-generated method stub
//		serialReceivedText.append(theString);							//append the text into the EditText
		//The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.
//		((ScrollView)serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);
        System.out.println(theString);
	}

}