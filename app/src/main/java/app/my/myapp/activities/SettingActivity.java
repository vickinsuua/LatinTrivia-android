package app.my.myapp.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import app.my.myapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.howToPlayButton)
    Button HowToPlay;
    @BindView(R.id.FAQSButton)
    Button FAQs;
    @BindView(R.id.Rules)
    Button Rules;
    @BindView(R.id.Privacy)
    Button Privacy;
    @BindView(R.id.termsConditions)
    Button Terms;
//    @BindView(R.id.ContactButton)
//    Button ContactUs;
    @BindView(R.id.QuestionButton)
    Button Questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
    }

    @OnClick(R.id.howToPlayButton)
    void onHowToPlay(){
        Intent intent = new Intent(SettingActivity.this, HowToPlayActivity.class);
        startActivity(intent);

    }


    @OnClick(R.id.Rules)
    void onRulesGame(){
        Intent intent = new Intent(SettingActivity.this, RulesActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.FAQSButton)
    void onFAQSGame(){
        Intent intent = new Intent(SettingActivity.this, FAQSActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.Privacy)
    void onPrivacy(){
        Intent intent = new Intent(SettingActivity.this, PrivacyActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.termsConditions)
    void onTerms(){
        Intent intent = new Intent(SettingActivity.this, TermsActivity.class);
        startActivity(intent);
        finish();
    }

//    @OnClick(R.id.ContactButton)
//    void onContact(){
//        Intent intent = new Intent(SettingActivity.this, ContactActivity.class);
//        startActivity(intent);
//        finish();
//    }


    @OnClick(R.id.QuestionButton)
    void onQuestion(){
        Intent intent = new Intent(SettingActivity.this, UploadQuestionActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.backToProfileButton)
    void onBackToProfile(){
        Intent intent = new Intent(SettingActivity.this, ProfileActivity.class);
        startActivity(intent);
    }






//    public void logout(View view){
//        LoginManager.getInstance().logOut();
//        goSignUp();
//    }
//
//    private void goSignUp() {
//        Intent intent = new Intent(SettingActivity.this, SignUpActivity.class);
//        startActivity(intent);
//        finish();
//    }


}
