package com.example.megaport.mynews.Controllers.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import com.example.megaport.mynews.Controllers.Utils.Notifications.NotificationHelper;
import com.example.megaport.mynews.Models.notifications.NotificationsPreferences;
import com.example.megaport.mynews.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.activity_notifications_switch) Switch mSwitch;
    @BindView(R.id.checkbox_container) LinearLayout mCheckboxContainer;
    @BindView(R.id.search_query_term_input) EditText mSearchQuery;
    @BindView(R.id.image_button_notifications_return) ImageButton mImageButton;

    public static final String PREFS = "PREFS";
    public static final String NOTIFICATIONS_STATE = "NOTIFICATIONS_STATE";
    private static final String PREF_KEY_QUERY_TERM = "PREF_KEY_QUERY_TERM";
    private static final String PREF_KEY_CATEGORY_LIST = "PREF_KEY_CATEGORY_LIST";

    public static final String NOTIFICATIONS_HOUR = "14";
    public static final String NOTIFICATIONS_MIN = "30";


    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        mImageButton.setOnClickListener( view -> startActivity() );

        this.retrievePreferences();
        this.configureSwitchChangeListener();
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveNotificationsPreferences(mSearchQuery.getText().toString(), getSelectedCheckboxes(), mSwitch.isChecked());
        if (mSwitch.isChecked()){
            toggleNotifications(true);
            Log.e("Notifications", "onPause: True" );
        }else{
            toggleNotifications(false);
            Log.e("Notifications", "onPause: False" );
        }
        Toast.makeText(NotificationsActivity.this, "Notifications preferences saved", Toast.LENGTH_SHORT).show();
    }

    // -------------
    // CONFIGURATION
    // -------------


    private void retrievePreferences(){
        mPreferences = getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<NotificationsPreferences>(){}.getType();
        String jsonState = mPreferences.getString(NOTIFICATIONS_STATE,"");
        NotificationsPreferences notificationsPreferences = gson.fromJson( jsonState, type );
        String queryTerm;
        if (notificationsPreferences == null){ // Normally use for first time launch
            queryTerm = mPreferences.getString(PREF_KEY_QUERY_TERM, NotificationsPreferences.DEFAULT_QUERY_TERM);
            String retrieve = mPreferences.getString(PREF_KEY_CATEGORY_LIST, NotificationsPreferences.DEFAULT_CATEGORY_LIST);
            List<String> categoryList = new ArrayList<>();
            categoryList.add(retrieve);
            saveNotificationsPreferences( queryTerm, categoryList, false);
        }else{
            queryTerm = notificationsPreferences.getQueryTerm();
            List<String> list = notificationsPreferences.getCategoryList();
            if (!(queryTerm.equals("")) && (!((list.isEmpty())))){
                mSearchQuery.setText( queryTerm );
                for (int x = 0; x < list.size(); x++){
                    for (int i = 0; i < mCheckboxContainer.getChildCount(); i++) {
                        View view = mCheckboxContainer.getChildAt(i);
                        if (view instanceof ViewGroup) {
                            ViewGroup viewGroup = ((ViewGroup)view);
                            for (int y = 0; y < viewGroup.getChildCount(); y++){
                                View viewChild = viewGroup.getChildAt(y);
                                if (viewChild instanceof CheckBox){
                                    CheckBox checkBox = ((CheckBox) viewChild);
                                    if (checkBox.getTag().toString().equals(list.get(x)) ){
                                        checkBox.setChecked(true);
                                    }
                                }
                            }
                        }
                    }
                }
                mSwitch.setChecked( notificationsPreferences.isEnabled());
            }
        }
    }

    private void saveNotificationsPreferences(String queryTerm, List<String> categoryList, boolean isEnabled){
        Gson gson = new Gson();
        NotificationsPreferences notifPrefs = new NotificationsPreferences(queryTerm,categoryList, isEnabled);
        String jsonNotifPrefs = gson.toJson(notifPrefs);
        mPreferences.edit().putString(NOTIFICATIONS_STATE,jsonNotifPrefs).apply();
    }

    private List<String> getSelectedCheckboxes(){
        List<String> selectedCheckboxes = new ArrayList<>();
        for (int i = 0; i < mCheckboxContainer.getChildCount(); i++) {
            View view = mCheckboxContainer.getChildAt(i);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = ((ViewGroup)view);
                for (int y = 0; y < viewGroup.getChildCount(); y++){
                    View viewChild = viewGroup.getChildAt(y);
                    if (viewChild instanceof CheckBox){
                        CheckBox checkBox = ((CheckBox) viewChild);
                        if (checkBox.isChecked()){
                            selectedCheckboxes.add(checkBox.getTag().toString());
                        }
                    }
                }
            }
        }
        return selectedCheckboxes;
    }

    private void configureSwitchChangeListener(){
        mSwitch.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            if (isChecked) {
                if (!(mSearchQuery.getText().toString().equals(""))){
                    List<String> selectedCheckboxes = getSelectedCheckboxes();
                    if (selectedCheckboxes.isEmpty()){
                        Toast.makeText(NotificationsActivity.this, "You have to select at least one category", Toast.LENGTH_SHORT).show();
                        buttonView.setChecked(false);
                    }
                }else{
                    Toast.makeText(NotificationsActivity.this, "Query term can't be empty to enable notifications", Toast.LENGTH_SHORT).show();
                    buttonView.setChecked(false);
                }
            }else{
                toggleNotifications(false);
            }
        } );
    }

    private void toggleNotifications(boolean enableNotifications){
        if (enableNotifications){
            NotificationHelper.scheduleRepeatingRTCNotification(getBaseContext(), NOTIFICATIONS_HOUR, NOTIFICATIONS_MIN);
        }else{
            NotificationHelper.cancelAlarmRTC();
        }
    }

    private void startActivity(){
        Intent intent = new Intent( this, MainActivity.class );
        startActivity(intent);
    }
}
