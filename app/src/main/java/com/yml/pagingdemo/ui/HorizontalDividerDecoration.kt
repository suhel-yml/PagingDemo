package com.yml.pagingdemo.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class HorizontalDividerDecoration : RecyclerView.ItemDecoration() {

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = 0xFFAAAAAA.toInt()
        strokeWidth = 0f
    }

    private val bounds = Rect()

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        repeat(parent.childCount - 1) { index ->
            val child = parent.getChildAt(index)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            canvas.drawLine(
                bounds.left.toFloat(),
                bounds.bottom.toFloat(),
                bounds.right.toFloat(),
                bounds.bottom.toFloat(),
                linePaint
            )
        }
    }

}