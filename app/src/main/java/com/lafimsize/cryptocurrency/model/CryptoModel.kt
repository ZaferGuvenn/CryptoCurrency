package com.lafimsize.cryptocurrency.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(


        //@SerializedName ("currency")//javada yapılıyormuş. bunu kullanmadığında aşağıdaki netteki veri ile aynı olmalı.
        val currency:String,

        //@SerializedName("Price")
        val price:String
)