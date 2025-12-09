package com.example.homework21.MultiType

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(
    private val verticalSpaceHeight: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // Для всех элементов кроме последнего добавляем отступ снизу
        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount?.minus(1)) {
            outRect.bottom = verticalSpaceHeight.dpToPx(view.context)
        }
    }
}

// Extension function для удобства
fun Int.dpToPx(context: Context): Int =
    (this * context.resources.displayMetrics.density).toInt()