package com.demo.testingproject.widget.loading

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.demo.testingproject.R

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflateView =
            inflater.inflate(R.layout.test_loading_dialog, findViewById(R.id.loading_cont))
        setContentView(inflateView)
    }
}
