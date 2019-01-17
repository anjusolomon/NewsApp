package com.example.newsfeed;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class Login extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener{

    private SignInButton sign_in;
    private GoogleApiClient googleApiClient;
    private final static int REQ_CODE=9001;
    private TextView Name, Email;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    Name=(TextView)findViewById(R.id.name);
    Email=(TextView)findViewById(R.id.email);
    NavigationView navigationView = findViewById(R.id.nav_view);

    sign_in=(SignInButton)findViewById(R.id.btn_login);
    sign_in.setOnClickListener(this);
    GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions
            .DEFAULT_SIGN_IN)
            .requestEmail()
            .build();

    googleApiClient=new GoogleApiClient.Builder(this)
            .enableAutoManage(this,this)
            .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
            .build();






}

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_login :
                signIn();
                break;

        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn(){

        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);

    }
//    private void signOut(){
//
//        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
//            @Override
//            public void onResult(@NonNull Status status) {
//                updateUI(false);
//            }
//        });

 //   }
    private void handleResult(GoogleSignInResult result){

        if(result.isSuccess())
        {
            //get user information
            GoogleSignInAccount account=result.getSignInAccount();
            String name=account.getDisplayName();
            Log.d("TAG", "name ");
            String email= account.getEmail();
            Log.d("TAG", "email ");


            //set data display in view
           // Name.setText(name);
            //Email.setText(email);

//
            //update UI
            updateUI(true);
        } else //if login fails
        {
            updateUI(false);
        }
    }

    private void updateUI(boolean isLogin){
        if(isLogin)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            sign_in.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE)
        {
            GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
