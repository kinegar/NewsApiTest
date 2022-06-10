package iglo.indocyber.api_service

import android.content.Context
import com.google.gson.Gson
import iglo.indocyber.api_service.ConstantService.BASE_URL
import iglo.indocyber.common.entity.ErrorData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.PrintWriter
import java.io.StringWriter

object RetrofitClient {
    fun getClient(): Retrofit{
//        Gander.setGanderStorage(GanderIMDB.getInstance())
//        val client = OkHttpClient.Builder().addInterceptor(GanderInterceptor(context).apply {
//            showNotification(true)
//        }).build()
        val client = OkHttpClient.Builder()
            .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY))
            .addInterceptor{
                try {
                    it.proceed(it.request())
                }catch (e:Exception){
                    val sw = StringWriter()
                    val pw = PrintWriter(sw)
                    e.printStackTrace(pw)

                    okhttp3.Response.Builder().code(0)
                        .body(
                            ResponseBody.create("application/json".toMediaTypeOrNull(), Gson().toJson(
                                ErrorData(sw.toString(),0)
                            ))).request(it.request())
                        .protocol(Protocol.HTTP_2)
                        .message(e.message.orEmpty())
                        .build()
                }
            }
            .build()
        return Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build()
    }
//    fun getClient(): Retrofit{
//        val client = OkHttpClient.Builder().build()
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL).client(client)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//    }
}