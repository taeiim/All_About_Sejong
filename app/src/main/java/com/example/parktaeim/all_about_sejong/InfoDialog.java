package com.example.parktaeim.all_about_sejong;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by parktaeim on 2018. 6. 16..
 */

public class InfoDialog extends android.support.v4.app.DialogFragment {
    private View rootView;
    private TextView infoTv;
    private Button cancelBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_info, container);

        cancelBtn = (Button) rootView.findViewById(R.id.dialogInfo_cancelBtn);
        infoTv = (TextView) rootView.findViewById(R.id.dialogInfo_infoTv);

        setDialog();

        return rootView;
    }

    private void setDialog() {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Bundle getArgs = getArguments();
        String infoText = getArgs.getString("infoText");

        infoTv.setText(infoText);
        cancelBtn.setOnClickListener(v -> dismiss());
    }

}
