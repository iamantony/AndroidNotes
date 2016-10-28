package com.iamantony.compass;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class CompassView extends View {

    private float m_bearing;
    private Paint m_markerPaint;
    private Paint m_textPaint;
    private Paint m_circlePaint;
    private String m_northStr;
    private String m_eastStr;
    private String m_southStr;
    private String m_westStr;
    private int m_textHeight;

    public CompassView(Context context) {
        super(context);
        initCompassView();
    }

    public CompassView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initCompassView();
    }

    public CompassView(Context context, AttributeSet attributeSet,
                       int defaultStyle) {
        super(context, attributeSet, defaultStyle);
        initCompassView();
    }

    public void setBearing(float bearing) {
        m_bearing = bearing;
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
    }

    public float getBearing() {
        return m_bearing;
    }

    protected void initCompassView() {
        setFocusable(true);

        Resources res = this.getResources();

        m_circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_circlePaint.setColor(res.getColor(R.color.colorBackground));
        m_circlePaint.setStrokeWidth(1);
        m_circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        m_northStr = res.getString(R.string.cardinal_north);
        m_eastStr = res.getString(R.string.cardinal_east);
        m_southStr = res.getString(R.string.cardinal_south);
        m_westStr = res.getString(R.string.cardinal_west);

        m_textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_textPaint.setColor(res.getColor(R.color.colorText));

        m_textHeight = (int)m_textPaint.measureText("yY");

        m_markerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_markerPaint.setColor(res.getColor(R.color.colorMarker));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measure(widthMeasureSpec);
        int height = measure(heightMeasureSpec);

        int dimension = Math.min(width, height);
        setMeasuredDimension(dimension, dimension);
    }

    private int measure(int measureSpect) {
        int result = 200;

        int specMode = MeasureSpec.getMode(measureSpect);
        int specSize = MeasureSpec.getSize(measureSpect);

        if (specMode != MeasureSpec.UNSPECIFIED) {
            result = specSize;
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measureWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();

        int px = measureWidth / 2;
        int py = measureHeight / 2;
        int radius = Math.min(px, py);

        canvas.drawCircle(px, py, radius, m_circlePaint);
        canvas.save();

        canvas.rotate(-m_bearing, px, py);

        int textWidth = (int)m_textPaint.measureText("W");
        int cardinalX = px - textWidth / 2;
        int cardinalY = py - radius + m_textHeight;

        for (int i = 0; i < 24; i++)
        {
            canvas.drawLine(px, py - radius, px, py - radius + 10,
                    m_markerPaint);

            canvas.save();

            canvas.translate(0, m_textHeight);

            // Draw the cardinal points
            if (i % 6 == 0)
            {
                String dirStr = "";
                switch(i) {
                    case (0) : {
                        dirStr = m_northStr;
                        int arrowY = 2 * m_textHeight;
                        canvas.drawLine(px, arrowY, px - 5, 3 * m_textHeight,
                                m_markerPaint);

                        canvas.drawLine(px, arrowY, px + 5, 3 * m_textHeight,
                                m_markerPaint);

                        break;
                    }
                    case (6) : dirStr = m_eastStr; break;
                    case (12) : dirStr = m_southStr; break;
                    case (18) : dirStr = m_westStr; break;
                }

                canvas.drawText(dirStr, cardinalX, cardinalY, m_textPaint);
            }
            else if (i % 3 == 0) {
                // Draw the text every alternate 45deg
                String angle = String.valueOf(i * 15);
                float angleTextWidth = m_textPaint.measureText(angle);
                int angleTextX = (int)(px - (angleTextWidth / 2));
                int angleTextY = py - radius + m_textHeight;
                canvas.drawText(angle, angleTextX, angleTextY, m_textPaint);
            }

            canvas.restore();
            canvas.rotate(15, px, py);
        }

        canvas.restore();
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(
            final AccessibilityEvent event) {
        super.dispatchPopulateAccessibilityEvent(event);
        if (isShown())
        {
            String bearingStr = String.valueOf(m_bearing);
            if (bearingStr.length() > AccessibilityEvent.MAX_TEXT_LENGTH) {
                bearingStr = bearingStr.substring(0,
                        AccessibilityEvent.MAX_TEXT_LENGTH);
            }

            event.getText().add(bearingStr);
            return true;
        }

        return false;
    }
}
