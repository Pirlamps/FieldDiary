package br.com.fielddiary.util.progress

import android.app.Activity
import android.content.Context
import android.view.Gravity.CENTER
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ProgressBar
import android.widget.RelativeLayout

class Progress(context: Context) {

    private var progress: ProgressBar? = null

    init {

        if (context is Activity) {
            val layout = context.findViewById<ViewGroup>(android.R.id.content).rootView as ViewGroup
            progress = ProgressBar(context, null, android.R.attr.progressBarStyle).apply {
                isIndeterminate = true
            }

            val params = RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            val relative = RelativeLayout(context).apply {
                gravity = CENTER
                addView(progress)
            }
            layout.addView(relative, params)
            hide()
        }
    }

    fun show() {
        progress?.visibility = VISIBLE
    }

    fun hide() {
        progress?.visibility = INVISIBLE
    }

}