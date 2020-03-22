package com.example.progressbutton

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton

class ProgressButtonWithFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var buttonText: String? = null
    private var progressBarDrawable: Drawable? = null
    private var progressBarHeight: Int = 0
    private var progressBarWidth: Int = 0

    private var progressBar: ProgressBar = ProgressBar(context)
    private var button: Button = MaterialButton(context)

    init {
        obtainAttributes(context, attrs)
        button.text = buttonText
        progressBar.apply {
            progressBarDrawable?.let { indeterminateDrawable = it }
            isVisible = false
        }
        addView(button, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val progressLayoutParams = LayoutParams(progressBarWidth, progressBarHeight).apply {
            gravity = Gravity.CENTER
        }
        addView(progressBar, progressLayoutParams)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        button.setOnClickListener(l)
    }

    override fun setEnabled(enabled: Boolean) {
        button.isEnabled = enabled
    }

    fun startProgress() {
        progressBar.isVisible = true
        button.text = ""
        isEnabled = false
    }

    fun stopProgress() {
        progressBar.isVisible = false
        button.text = buttonText
        isEnabled = true
    }

    @SuppressLint("Recycle")
    private fun obtainAttributes(context: Context, attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.ProgressButtonWithFrameLayout)
            .autoRecycle { typedArray ->
                buttonText = typedArray.getString(R.styleable.ProgressButtonWithFrameLayout_buttonText)
                    ?: context.getString(
                        typedArray.getResourceId(
                            R.styleable.ProgressButtonWithFrameLayout_buttonText,
                            0
                        )
                    )
                progressBarDrawable =
                    typedArray.getDrawable(R.styleable.ProgressButtonWithFrameLayout_progressBarDrawable)
                progressBarHeight =
                    typedArray.getDimensionPixelSize(R.styleable.ProgressButtonWithFrameLayout_progressBarHeight, 0)
                progressBarWidth =
                    typedArray.getDimensionPixelSize(R.styleable.ProgressButtonWithFrameLayout_progressBarWidth, 0)
            }
    }
}