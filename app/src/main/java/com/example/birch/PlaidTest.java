package com.example.birch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.birch.network.LinkToken;
import com.example.birch.network.LinkTokenRequester;
import com.plaid.internal.core.crashreporting.internal.models.SentryProject;
import com.plaid.link.OpenPlaidLink;
import com.plaid.link.Plaid;
import com.plaid.link.configuration.LinkTokenConfiguration;
import com.plaid.link.result.LinkExit;
import com.plaid.link.result.LinkSuccess;
import com.plaid.*;
import kotlin.Unit;

public class PlaidTest extends AppCompatActivity {
    private TextView result;
    private TextView tokenResult;
    private TextView accessToken;
    private LinkSuccess success;

    private ActivityResultLauncher<LinkTokenConfiguration> linkAccountToPlaid = registerForActivityResult(
            new OpenPlaidLink(),
            result -> {
                if (result instanceof LinkSuccess) {
                    showSuccess((LinkSuccess) result);
                    //getAccessTok();
                } else {
                    showFailure((LinkExit) result);
                }
            });

    private void showSuccess(LinkSuccess success) {
        tokenResult.setText(getString(R.string.public_token_result, success.getPublicToken()));
        result.setText(getString(R.string.content_success));
        String publicTok = success.getPublicToken();
        Log.i("LOOK -------->>", publicTok);
        System.out.println(publicTok);

    }
    private void showFailure(LinkExit exit) {
        tokenResult.setText("");
        if (exit.getError() != null) {
            result.setText(getString(
                    R.string.content_exit,
                    exit.getError().getDisplayMessage(),
                    exit.getError().getErrorCode()));
        } else {
            result.setText(getString(
                    R.string.content_cancel,
                    exit.getMetadata().getStatus() != null ? exit.getMetadata().getStatus().getJsonValue() : "unknown"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plaid_test);

        result = findViewById(R.id.result);
        tokenResult = findViewById(R.id.public_token_result);
        accessToken = findViewById(R.id.access);

        View button = findViewById(R.id.open_link);
        button.setOnClickListener(view -> {
            setOptionalEventListener();
            openLink();
        });


    }

    private void setOptionalEventListener() {
        Plaid.setLinkEventListener(linkEvent -> {
            Log.i("Event", linkEvent.toString());
            return Unit.INSTANCE;
        });
    }
    private void openLink() {
        LinkTokenRequester.INSTANCE.getToken()
                .subscribe(this::onLinkTokenSuccess, this::onLinkTokenError);
    }
    private void onLinkTokenSuccess(String token) {
        LinkTokenConfiguration configuration = new LinkTokenConfiguration.Builder()
                .token(token)
                .build();
        linkAccountToPlaid.launch(configuration);
    }
    private void onLinkTokenError(Throwable error) {
        if (error instanceof java.net.ConnectException) {
            Toast.makeText(
                    this,
                    "Please run `sh start_server.sh <client_id> <sandbox_secret>`",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void getAccessTok(LinkSuccess success)
    {
        //LinkTokenRequester.INSTANCE.getAccToken();
    }
    private void onAccLinkTokenSuccess()
    {}
}