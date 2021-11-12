package com.example.emailweb

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract

class GetEmail {

    @SuppressLint("Range")
    fun showEmail(context: Context): ArrayList<String>
    {
        //    val nameList = ArrayList<String>()
        //   val phoneList = ArrayList<String>()
        val emailList = ArrayList<String>()

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
                if (cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)).toInt() > 0
                ) {
                    // Query phone here. Covered next
                    val pCur: Cursor? = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = ?", arrayOf(id), null)
                    while (pCur!!.moveToNext()) {
                        /*// Do something with phones
                        val phoneNo: String = pCur
                            .getString(
                                pCur.getColumnIndex(CommonDataKinds.Phone.NUMBER)
                            )
                        nameList.add(name) // Here you can list of contact.
                        phoneList.add(phoneNo) // Here you will get list of phone number.*/
                        val emailCur: Cursor? = cr.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            arrayOf(id),
                            null
                        )
                        while (emailCur!!.moveToNext()) {
                            val email: String =
                                emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                            emailList.add(email)// Here you will get list of email
                        }


                    }
                    pCur.close()
                }
            }
        }
        return emailList
    }

}