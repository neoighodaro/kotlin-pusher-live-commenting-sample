package com.example.android.pushersample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var arrayList: ArrayList<String> = ArrayList()

        // Initialize our adapter
        val adapter = RecyclerViewAdapter(this)

        // assign a layout manager to the recycler view
        recycler_view.layoutManager = LinearLayoutManager(this)

        // assign adapter to the recycler view
        recycler_view.adapter = adapter



        val options = PusherOptions()
        options.setCluster("eu")
        val pusher = Pusher("f1dc462a7b662a89ec08", options)

        //subscribe to a channel
        val channel = pusher.subscribe("my-channel")

        // this listener recieves any new message
        channel.bind("my-event") { channelName, eventName, data ->
            val jsonObject = JSONObject(data)
            arrayList.add(jsonObject.getString("message"))
            Log.d("TAG",jsonObject.getString("message"))
            runOnUiThread { adapter.setList(arrayList) }
        }
        pusher.connect()


        button_send.setOnClickListener(View.OnClickListener {
            if (edit_text.text.length>0) {
                sendMessage(edit_text.text.toString())
            }
        })



    }

    fun sendMessage(message:String) {
        val call = RetrofitClient().getClient().sendMessage(message)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                edit_text.setText("")
                hideKeyboard(this@MainActivity)
                Log.d("TAG","success")
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("TAG", t.printStackTrace().toString())
            }
        })
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
