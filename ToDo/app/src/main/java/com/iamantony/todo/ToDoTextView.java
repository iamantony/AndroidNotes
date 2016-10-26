package com.iamantony.todo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class ToDoTextView extends TextView {

    private int m_color;
    private Paint m_linePaint;

    public ToDoTextView(Context context, AttributeSet attributeSet, int ds) {
        super(context, attributeSet, ds);
        init();
    }

    public ToDoTextView(Context context) {
        super(context);
        init();
    }

    public ToDoTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void SetColor(int color) {
        // TODO: validate color value
        m_color = color;
    }

    private void init() {
        Resources resources = getResources();

        SetColor(resources.getColor(R.color.colorTextItem));

        m_linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_linePaint.setColor(resources.getColor(R.color.colorTextItemBorder));
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(m_color);

        canvas.drawLine(0, 0, 0, getMeasuredHeight(), m_linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), m_linePaint);

        canvas.save();

        super.onDraw(canvas);
        canvas.restore();
    }
}
