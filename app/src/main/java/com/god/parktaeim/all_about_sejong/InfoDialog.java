package com.god.parktaeim.all_about_sejong;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.R;


/**
 * Created by parktaeim on 2018. 6. 16..
 */

public class InfoDialog extends android.support.v4.app.DialogFragment {
    private View rootView;
    private TextView infoTv;
    private TextView infoSmallTv;
    private Button cancelBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_info, container);

        cancelBtn = (Button) rootView.findViewById(R.id.dialogInfo_cancelBtn);
        infoTv = (TextView) rootView.findViewById(R.id.dialogInfo_infoTv);
        infoSmallTv = (TextView) rootView.findViewById(R.id.dialogInfo_infoSmallTv);

        setDialog();

        return rootView;
    }

    private void setDialog() {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Bundle getArgs = getArguments();
        String infoText = getArgs.getString("infoText");
        String smallText = getArgs.getString("smallText");
        int defaultTextSize = getArgs.getInt("defaultTextSize");

        infoTv.setText(infoText);
        if (smallText != null) {
            infoSmallTv.setVisibility(View.VISIBLE);
            infoSmallTv.setText(smallText);
        }

        if(defaultTextSize != 0){
            infoTv.setTextSize(defaultTextSize);
        }

        cancelBtn.setOnClickListener(v -> dismiss());
    }

}
