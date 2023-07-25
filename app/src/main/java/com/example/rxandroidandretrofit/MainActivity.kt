package com.example.rxandroidandretrofit

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var mDataAdapter: DataAdapter
    private lateinit var rcvData: RecyclerView
    private lateinit var mListData : List<ObjectData>
    private lateinit var mDisposable : Disposable
    private lateinit var mProgressDialog: ProgressDialog

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCallApi = findViewById<Button>(R.id.btn_call_api)
        rcvData = findViewById(R.id.rcv_data)

        val linearLayout = LinearLayoutManager(this)
        rcvData.layoutManager = linearLayout

        mListData= mutableListOf()
        mProgressDialog= ProgressDialog(this)

        //đường kẻ phân cách các item của recycleview
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcvData.addItemDecoration(dividerItemDecoration)
        btnCallApi.setOnClickListener {
            onClickCallApi()
        }
    }

    private fun onClickCallApi() {
        mProgressDialog.show()
        ApiService.apiService.callApi()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<ObjectData>> {
                override fun onSubscribe(d: Disposable) {
                    mDisposable=d
                }

                override fun onNext(t: List<ObjectData>) {
                    mListData=t
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, "Call API Error", Toast.LENGTH_LONG).show()
                }

                override fun onComplete() {
                    mProgressDialog.dismiss()
                    Toast.makeText(this@MainActivity, "Call API success", Toast.LENGTH_LONG).show()
                    mDataAdapter = DataAdapter(mListData)
                    rcvData.adapter = mDataAdapter
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mDisposable!=null){
            mDisposable.dispose()
        }
    }
}