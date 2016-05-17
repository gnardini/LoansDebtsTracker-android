package com.gnardini.frienddebttracker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gnardini.frienddebttracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyView extends LinearLayout {

    @BindView(R.id.empty_title)
    TextView title;

    @BindView(R.id.empty_subtitle)
    TextView subtitle;

    public EmptyView(Context context) {
        super(context);
        init(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        inflate(context, R.layout.empty_layout, this);
        ButterKnife.bind(this);
        populateFromAttributes(context, attrs);
    }

    private void populateFromAttributes(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.EmptyView, 0, 0);
        String title = attributes.getString(R.styleable.EmptyView_empty_title);
        String subtitle = attributes.getString(R.styleable.EmptyView_empty_subtitle);
        attributes.recycle();

        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        if (!TextUtils.isEmpty(subtitle)) {
            setSubtitle(subtitle);
        }
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setSubtitle(String subtitle) {
        this.subtitle.setText(subtitle);
    }

}
