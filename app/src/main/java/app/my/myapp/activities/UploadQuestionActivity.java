package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Category;
import app.my.myapp.models.ListCategory;
import app.my.myapp.models.Question;
import app.my.myapp.models.Question;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UploadQuestionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner categories;


    @BindView(R.id.uploadQuestionButton)
    Button questionButton;
    @BindView(R.id.questionEditText)
    EditText questionView;
    @BindView(R.id.correctAnswerEditText)
    EditText correctAnswerView;
    @BindView(R.id.optionBEditText)
    EditText optionBView;
    @BindView(R.id.optionCEditText)
    EditText optionCView;
    @BindView(R.id.categorySpinner)
    Spinner categoryView;

    public String[] arrayCategories;
    public List<Category> listCategories;
    public String idCategory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_question);
        ButterKnife.bind(this);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.103:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<ListCategory> categories = api.getCategory();

        categories.enqueue(new Callback<ListCategory>() {
            @Override
            public void onResponse(Call<ListCategory> call, Response<ListCategory> response) {
                if (response.isSuccessful()) {

                    pushCategories(response);
                } else {
                }
            }

            @Override
            public void onFailure(Call<ListCategory> call, Throwable t) {
                Log.e( "catch: ", t.getMessage() );
            }
        });
    }

    private void pushCategories(Response<ListCategory> response) {

        categories = (Spinner) findViewById(R.id.categorySpinner);

        listCategories = response.body().getCategories();


        arrayCategories = new String[response.body().getCategories().size()];

        for (int i = 0; i < response.body().getCategories().size(); i++){
            arrayCategories[i] = response.body().getCategories().get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayCategories);

        categories.setAdapter(adapter);

        categories.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         idCategory = listCategories.get(position).get_id();
        Toast.makeText(UploadQuestionActivity.this, "Id"+idCategory , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * On join click
     */
    @OnClick(R.id.uploadQuestionButton)
    void onJoinClick() {
        if (checkData()) {
            Question question = new Question(
                    questionView.getText().toString(),
                    correctAnswerView.getText().toString(),
                    optionBView.getText().toString(),
                    optionCView.getText().toString(),
                    idCategory
            );
            Log.e("ERRRRROOROROOR: ", question.toString());
            sendNetworkRequest(question);
        }
    }

    /**
     * Check data
     * @return True/false
     */
    private boolean checkData() {
        if (questionView.getText().length() <= 0 || correctAnswerView.getText().length() <= 0 || optionBView.getText().length() <= 0 || optionBView.getText().length() <= 0 || optionCView.getText().length() <= 0 || idCategory.length() <= 0 ) {
            return false;
        }

        return true;
    }

    private void sendNetworkRequest(Question question){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.237.158.103:3000")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        Call<Question> call = api.uploadQuestion(question);

        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                Toast.makeText(UploadQuestionActivity.this, "YESSSSS", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Toast.makeText(UploadQuestionActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.e("ERRRRROOROROOR: ", t.getMessage());
            }
        });
    }

    @OnClick(R.id.backQuestionButton)
    void onBackToMenu(){
        Intent intent = new Intent(UploadQuestionActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
