package com.example.broadcastrcvtesting.ext

import android.widget.ImageView
import com.example.broadcastrcvtesting.R
import com.squareup.picasso.Picasso

internal fun image_helper(resource : String, idView : ImageView) {
    Picasso.get()
        .load(resource)
        .error(R.drawable.peakpx__1_)
        .into(idView)
}