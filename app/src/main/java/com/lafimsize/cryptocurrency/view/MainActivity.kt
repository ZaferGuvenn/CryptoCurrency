package com.lafimsize.cryptocurrency.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.cryptocurrency.R
import com.lafimsize.cryptocurrency.adapter.RecyclerAdapter
import com.lafimsize.cryptocurrency.databinding.ActivityMainBinding
import com.lafimsize.cryptocurrency.model.CryptoModel
import com.lafimsize.cryptocurrency.service.CryptoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerAdapter.Listener/*implementationmuş*/ {
    private val baseUrl="https://raw.githubusercontent.com"
    private lateinit var cryptoModel: ArrayList<CryptoModel>
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter:RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        cryptoModel= ArrayList<CryptoModel>()
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        binding.recyclerView.layoutManager=LinearLayoutManager(this)


        loadData()
    }
    fun loadData(){
        val retrofit=Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        //servisimiz()cryptoapi
        val service=retrofit.create(CryptoApi::class.java)
        val call=service.getData()

        //çağırıyoruz. Asenkron bir şekilde sıraya almak.
        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {
                if (response.isSuccessful){
                    //nullable olarak geliyor bu yüzden let ile kontrol.
                    response.body()?.let {
                        cryptoModel=ArrayList(it)//kendisi zaten arrayliste cevirecekmiş.

                        /*yazdırıyoruz verileri
                        for (i in cryptoModel){
                            println(i.currency+"= "+i.price)
                        }*/

                        cryptoModel?.let {
                            recyclerAdapter= RecyclerAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter=recyclerAdapter
                        }

                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Price is:${cryptoModel.price}",Toast.LENGTH_SHORT).show()
    }
}