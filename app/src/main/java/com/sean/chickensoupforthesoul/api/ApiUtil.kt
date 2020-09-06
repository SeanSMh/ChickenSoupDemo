package com.sean.chickensoupforthesoul.api

import android.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtil {

    private const val URL_CS = "https://api.tianapi.com/txapi/dujitang/"
    private const val URL_WX = "https://qyapi.weixin.qq.com/cgi-bin/webhook/"

    private val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
        Log.e(
            "Sean--->",
            message
        )
    }).setLevel(HttpLoggingInterceptor.Level.BODY)


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()


    private fun getApi(type: Int): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(getUrl(type))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getUrl(type: Int): String {
        return if (type == 0) {
            URL_CS
        } else {
            URL_WX
        }

    }


    fun requestChickenSoul() {
        val apiService = getApi(0).create(ApiService::class.java)
        val call = apiService.chickenSoup.enqueue(object : Callback<ChickenSoupEntity> {
            override fun onFailure(call: Call<ChickenSoupEntity>, t: Throwable) {
                Log.d("Sean--->OnFailure", t.toString())
            }

            override fun onResponse(
                call: Call<ChickenSoupEntity>,
                response: Response<ChickenSoupEntity>
            ) {
                response.body()?.let {
                    Log.d("Sean--->OnResponse", it.newsList[0].content.toString())
                    postWXMessage(it.newsList[0].content.toString())
                }
            }

        })

    }

    fun postWXMessage(content: String) {
        val apiService = getApi(1).create(ApiService::class.java)

        val jsonContent = JSONObject()
        jsonContent.put("content", content)
        val jsonObject = JSONObject()
        jsonObject.put("text", jsonContent)
        jsonObject.put("msgtype", "text")

        val requestBody = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            jsonObject.toString()
        )

        val call = apiService.postWXMessage(requestBody).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Sean--->发送失败", t.toString())
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.e("Sean--->发送成功", response.toString())
            }

        })
    }


}