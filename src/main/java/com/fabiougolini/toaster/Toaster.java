package com.fabiougolini.toaster;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Toaster extends Toast {
    private Context context;
    private View layout;
    private TextView ctv_message;
    private int backgroundColor;
    private int marginBottom = 150;

    public Toaster(Context context) {
        super(context);
        this.context = context;

        init();
    }

    public void init() {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        layout = inflater.inflate((R.layout.toaster), (ViewGroup) ((Activity)context).findViewById(R.id.toast_layout_root));

        ctv_message = layout.findViewById(R.id.text);

        this.setGravity(Gravity.BOTTOM, 0, 150);
        this.setDuration(Toast.LENGTH_LONG);
        this.setView(layout);
    }

    public void showMessage(String message) {
        ctv_message.setText(message);
        this.show();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        layout.getRootView().setBackgroundColor(backgroundColor);
    }

    public static void done(Context context, String message) {
        showMessage(context, message, R.drawable.ic_done_black_24dp, context.getResources().getColor(R.color.done, null));
    }

    public static void info(Context context, String message) {
        showMessage(context, message, R.drawable.ic_info_black_24dp, context.getResources().getColor(R.color.info, null));
    }

    public static void error(Context context, String message) {
        showMessage(context, message, R.drawable.ic_close_black_24dp, context.getResources().getColor(R.color.error, null));
    }

    public static void showMessage(Context context, String message, @Nullable int icon, @Nullable int backgroundColor) {
        Toaster t = new Toaster(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View layout = inflater.inflate((R.layout.toaster), (ViewGroup) ((Activity)context).findViewById(R.id.toast_layout_root));

        TextView ctv_message = layout.findViewById(R.id.text);
        ImageView mIcon = layout.findViewById(R.id.image);
        ctv_message.setText(message);

        if(icon != 0){
            mIcon.setImageDrawable(context.getResources().getDrawable(icon, null));
        }

        if(backgroundColor != 0){
            layout.getBackground().setColorFilter(backgroundColor, PorterDuff.Mode.SRC_ATOP);
        }

        t.setGravity(Gravity.BOTTOM, 0, 150);
        t.setDuration(Toast.LENGTH_LONG);
        t.setView(layout);
        t.show();
    }
}