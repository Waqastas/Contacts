package com.example.emailweb

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.AggregationExceptions.CONTENT_URI

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.provider.ContactsContract.CommonDataKinds
import android.provider.Telephony.Mms.Addr.CONTACT_ID
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email:GetEmail= GetEmail()
        val web:GetWeb= GetWeb()



        Log.d("email",email.showEmail(applicationContext).toString())
       Log.d("Web",web.getWeb(applicationContext).toString())

       emailText.text=web.getWeb(applicationContext).toString()

    }
}