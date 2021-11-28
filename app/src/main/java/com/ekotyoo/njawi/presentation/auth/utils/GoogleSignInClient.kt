package com.ekotyoo.njawi.presentation.auth.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.ekotyoo.njawi.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

fun createIntent(context: Context, input: Int?): Intent {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("381802667272-hkb8lmbln91j1jejpbghasgb4bp1ufh1.apps.googleusercontent.com")
        .requestEmail()
        .build();
    val intent =  GoogleSignIn.getClient(context,gso)
    return intent.signInIntent
}

fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
    return when (resultCode) {
        Activity.RESULT_OK -> {
            GoogleSignIn.getSignedInAccountFromIntent(intent)
        }
        else -> null
    }
}