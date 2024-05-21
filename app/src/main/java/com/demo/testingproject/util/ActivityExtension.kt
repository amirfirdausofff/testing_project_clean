package com.demo.testingproject.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_VIEW
import android.content.Intent.EXTRA_EMAIL
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.content.Intent.createChooser
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable


inline fun <reified T : AppCompatActivity> Activity.startActivity(actionIntent: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java).apply(actionIntent)
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> Activity.startActivity(vararg params: Pair<String, *>) {
    startActivity(
        IntentHelper.createIntent(
            this,
            T::class.java,
            params
        )
    )
}

inline fun <reified T : AppCompatActivity> Activity.startActivity(
    vararg params: Pair<String, *>,
    actionIntent: Intent.() -> Unit = {}
) {
    startActivity(
        IntentHelper.createIntent(
            this,
            T::class.java,
            params
        ).apply(actionIntent)
    )
}

inline fun <reified T : AppCompatActivity> Activity.startActivityForResult(
    requestCode: Int,
    vararg params: Pair<String, *>
) {
    startActivityForResult(IntentHelper.createIntent(this, T::class.java, params), requestCode)
}

fun Activity.startActivityActionView(actionLink: String) {
    val intent = Intent(ACTION_VIEW, Uri.parse(actionLink))
    startActivity(intent)
}

fun Activity.startActivityActionSend(
    title: String? = null,
    intentType: String,
    extraText: String? = null,
    extraSubject: String? = null,
    extraEmail: String? = null
) {
    val intent = Intent(ACTION_SEND)
    intent.apply {
        type = intentType
        extraText?.let { putExtra(EXTRA_TEXT, it) }
        extraSubject?.let { putExtra(EXTRA_SUBJECT, it) }
        extraEmail?.let { putExtra(EXTRA_EMAIL, arrayOf(it)) }
    }
    startActivity(createChooser(intent, title))
}

object IntentHelper {
    fun <T> createIntent(
        ctx: Context,
        clazz: Class<out T>,
        params: Array<out Pair<String, Any?>>
    ): Intent {
        val intent = Intent(ctx, clazz)
        if (params.isNotEmpty()) createArguments(
            intent,
            params
        )
        return intent
    }

    private fun createArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
        params.forEach {
            when (val value = it.second) {
                null -> intent.putExtra(it.first, null as Serializable?)
                is Int -> intent.putExtra(it.first, value)
                is Long -> intent.putExtra(it.first, value)
                is CharSequence -> intent.putExtra(it.first, value)
                is String -> intent.putExtra(it.first, value)
                is Float -> intent.putExtra(it.first, value)
                is Double -> intent.putExtra(it.first, value)
                is Char -> intent.putExtra(it.first, value)
                is Short -> intent.putExtra(it.first, value)
                is Boolean -> intent.putExtra(it.first, value)
                is Serializable -> intent.putExtra(it.first, value)
                is Bundle -> intent.putExtra(it.first, value)
                is Parcelable -> intent.putExtra(it.first, value)
                is Array<*> -> when {
                    value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                    else -> throw IllegalStateException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                }

                is IntArray -> intent.putExtra(it.first, value)
                is LongArray -> intent.putExtra(it.first, value)
                is FloatArray -> intent.putExtra(it.first, value)
                is DoubleArray -> intent.putExtra(it.first, value)
                is CharArray -> intent.putExtra(it.first, value)
                is ShortArray -> intent.putExtra(it.first, value)
                is BooleanArray -> intent.putExtra(it.first, value)
                else -> throw java.lang.IllegalStateException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            return@forEach
        }
    }
}
