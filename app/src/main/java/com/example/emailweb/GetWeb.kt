package com.example.emailweb

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract

class GetWeb {


    @SuppressLint("Range")
    fun getWeb(context: Context): ArrayList<String>
    {


        val webList=ArrayList<String>()

        val cr = context.contentResolver
        val cur: Cursor? = cr.query(
            ContactsContract.Contacts.CONTENT_URI, null,
            null, null, null
        )

        if (cur!!.getCount() > 0) {
            while (cur.moveToNext()) {
                val id: String = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts._ID)
                )
                //val name: String = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                if (cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        .toInt() > 0) {
                    // Query phone here. Covered next
                    val pCur: Cursor? = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = ?", arrayOf(id), null
                    )
                    while (pCur!!.moveToNext()) {
                        val projection = arrayOf(
                            ContactsContract.CommonDataKinds.Website.URL
                        )
                        val selection: String =
                            ContactsContract.Data.CONTACT_ID.toString() + " = " + id + " AND " + ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE + "'"

                        val webCur = context.contentResolver.query(
                            ContactsContract.Data.CONTENT_URI,
                            projection,
                            selection,
                            null,
                            null
                        )

                        while (webCur!!.moveToNext()) {
                            val web: String =
                                webCur.getString(webCur.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL))
                            webList.add(web) // Here you will get list of email
                        }
                        webCur.close()
                    }
                }

            }
        }
        return webList
    }


}