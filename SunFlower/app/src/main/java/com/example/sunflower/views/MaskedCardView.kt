package com.example.sunflower.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.R
import com.google.android.material.card.MaterialCardView

class MaskedCardView @JvmOverloads constructor(context: Context,
                                               attrs: AttributeSet? = null,
                                               defStyle: Int = R.attr.materialCardViewStyle)
    : MaterialCardView(context,attrs,defStyle)  {

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
}