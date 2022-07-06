package com.kaushal.khat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import androidx.cardview.widget.CardView;
import android.widget.EditText;
import com.google.android.material.button.*;
import java.util.Timer;
import java.util.TimerTask;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import android.view.View;
import android.graphics.Typeface;
import android.content.ClipData;
import android.content.ClipboardManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> mapp = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout splash_screen;
	private LinearLayout login;
	private LinearLayout sign_up;
	private LinearLayout splash_main;
	private LinearLayout linear8;
	private LinearLayout linear7;
	private ImageView imageview1;
	private TextView textview3;
	private ProgressBar progressbar1;
	private TextView textview4;
	private CardView login_box;
	private TextView textview2;
	private LinearLayout linear2;
	private TextView textview1;
	private EditText edittext1;
	private EditText edittext2;
	private MaterialButton materialbutton1;
	private LinearLayout linear5;
	private CardView cardview1;
	private TextView textview6;
	private LinearLayout linear6;
	private TextView textview7;
	private EditText edittext5;
	private EditText edittext6;
	private MaterialButton materialbutton3;
	
	private TimerTask timerr;
	private RequestNetwork request;
	private RequestNetwork.RequestListener _request_request_listener;
	private AlertDialog.Builder dialog;
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private Intent intent = new Intent();
	private SharedPreferences shared;
	
	private OnCompleteListener fcm_onCompleteListener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		splash_screen = (LinearLayout) findViewById(R.id.splash_screen);
		login = (LinearLayout) findViewById(R.id.login);
		sign_up = (LinearLayout) findViewById(R.id.sign_up);
		splash_main = (LinearLayout) findViewById(R.id.splash_main);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview3 = (TextView) findViewById(R.id.textview3);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		textview4 = (TextView) findViewById(R.id.textview4);
		login_box = (CardView) findViewById(R.id.login_box);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview1 = (TextView) findViewById(R.id.textview1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		materialbutton1 = (MaterialButton) findViewById(R.id.materialbutton1);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		cardview1 = (CardView) findViewById(R.id.cardview1);
		textview6 = (TextView) findViewById(R.id.textview6);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		textview7 = (TextView) findViewById(R.id.textview7);
		edittext5 = (EditText) findViewById(R.id.edittext5);
		edittext6 = (EditText) findViewById(R.id.edittext6);
		materialbutton3 = (MaterialButton) findViewById(R.id.materialbutton3);
		request = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		auth = FirebaseAuth.getInstance();
		shared = getSharedPreferences("shared", Activity.MODE_PRIVATE);
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				login.setVisibility(View.GONE);
				sign_up.setVisibility(View.VISIBLE);
			}
		});
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				auth.signInWithEmailAndPassword(edittext1.getText().toString(), edittext2.getText().toString()).addOnCompleteListener(MainActivity.this, _auth_sign_in_listener);
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				login.setVisibility(View.VISIBLE);
				sign_up.setVisibility(View.GONE);
			}
		});
		
		materialbutton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				auth.createUserWithEmailAndPassword(edittext5.getText().toString(), edittext6.getText().toString()).addOnCompleteListener(MainActivity.this, _auth_create_user_listener);
			}
		});
		
		_request_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
					intent.setAction(Intent.ACTION_VIEW);
					intent.setClass(getApplicationContext(), ConversationActivity.class);
					startActivity(intent);
					finish();
				}
				else {
					timerr = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									progressbar1.setVisibility(View.GONE);
									textview4.setVisibility(View.GONE);
									login_box.setVisibility(View.VISIBLE);
									login.setVisibility(View.VISIBLE);
								}
							});
						}
					};
					_timer.schedule(timerr, (int)(1500));
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				dialog.setTitle("No internet connection!");
				dialog.setMessage("No internet connection please connect to internet and try again...");
				dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						request.startRequestNetwork(RequestNetworkController.GET, "https://google.com", "labalaba", _request_request_listener);
					}
				});
				dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finishAffinity();
					}
				});
				dialog.create().show();
			}
		};
		
		fcm_onCompleteListener = new OnCompleteListener<InstanceIdResult>() {
			@Override
			public void onComplete(Task<InstanceIdResult> task) {
				final boolean _success = task.isSuccessful();
				final String _token = task.getResult().getToken();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				if (_success) {
					SketchwareUtil.showMessage(getApplicationContext(), _token);
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _token));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					auth.signInWithEmailAndPassword(edittext5.getText().toString(), edittext6.getText().toString()).addOnCompleteListener(MainActivity.this, _auth_sign_in_listener);
					map = new HashMap<>();
					map.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), FirebaseAuth.getInstance().getCurrentUser().getEmail().substring((int)(0), (int)(4)).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(999))))));
					
					map.clear();
				}
				else {
					com.google.android.material.snackbar.Snackbar.make(linear1, _errorMessage, com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
							 
						}
					}).show();
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					SketchwareUtil.CustomToast(getApplicationContext(), "Login Successfull", 0xFFFFFFFF, 12, 0xFF0288D1, 11, SketchwareUtil.BOTTOM);
					intent.setAction(Intent.ACTION_VIEW);
					intent.setClass(getApplicationContext(), ConversationActivity.class);
					startActivity(intent);
					finish();
				}
				else {
					com.google.android.material.snackbar.Snackbar.make(linear1, _errorMessage, com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
							 
						}
					}).show();
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		login_box.setCardBackgroundColor(0xFFFFFFFF);
		login_box.setRadius((float)27);
		login_box.setCardElevation((float)12);
		login_box.setPreventCornerOverlap(false);
		login_box.setUseCompatPadding(true);
		cardview1.setCardBackgroundColor(0xFFFFFFFF);
		cardview1.setRadius((float)27);
		cardview1.setCardElevation((float)12);
		cardview1.setPreventCornerOverlap(false);
		cardview1.setUseCompatPadding(true);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 1);
		edittext5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		edittext6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		materialbutton3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/arr.ttf"), 0);
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF0288D1));
		edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF0288D1));
		edittext5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF0288D1));
		edittext6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF0288D1));
		login.setVisibility(View.GONE);
		sign_up.setVisibility(View.GONE);
		timerr = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						request.startRequestNetwork(RequestNetworkController.GET, "https://google.com", "labalaba", _request_request_listener);
					}
				});
			}
		};
		_timer.schedule(timerr, (int)(2000));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}