package com.god.parktaeim.all_about_sejong.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.god.parktaeim.all_about_sejong.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.observers.DisposableObserver
import org.reactivestreams.Subscriber

/**
 * Created by parktaeim on 2018. 9. 20..
 */

class BicycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicycle)



    }
}