package com.gustavo.wubalubadubdub.di.module

import android.content.Context
import android.widget.Toast
import com.gustavo.wubalubadubdub.BuildConfig
import com.gustavo.wubalubadubdub.R
import com.gustavo.wubalubadubdub.source.remote.Api
import com.gustavo.wubalubadubdub.utils.*
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Provides
import dagger.Module
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.text.ParseException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun getInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            Timber.i(message)
        }
        interceptor.level = BuildConfig.INTERCEPTOR_LEVEL
        return interceptor
    }

    @Provides
    @Reusable
    @JvmStatic
    fun getOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(600, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(UnauthorisedInterceptor())
            .build()
    }

    @Provides
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(customDateAdapter)
            .build()
    }

    var customDateAdapter: Any = object : Any() {
        val dateFormatUtil = DateFormatUtil()

        @ToJson
        @Synchronized
        fun dateToJson(d: Date): String {
            return dateFormatUtil.format(d)
        }

        @FromJson
        @Synchronized
        @Throws(ParseException::class)
        fun dateToJson(s: String): Date? {
            return dateFormatUtil.parse(s)
        }
    }

    class UnauthorisedInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            Timber.tag("NetworkModuleResponse").e(response.message())
            when {
                response.message().contains("Unable to resolve host") ->{
                    RxBus.publish(RxEvent.EventMessageRef(R.string.url_not_found))
                }
                response.code() == 404 -> {
                    // post message
                    RxBus.publish(RxEvent.EventMessageRef(R.string.url_not_found))
                }
                response.code() != 200 -> {
                    unhandledTry {
                        sendErrorMessage(response.peekBody(Long.MAX_VALUE).string())
                    }
                }
            }
            return response
        }
    }

    private fun getServerError(errorBody: String?): String? {
        if (errorBody == null) return null
        errorBody.let { responseBody ->
            return try {
                val errorJson = JSONObject(responseBody)
                val messageError =
                    if (errorJson.has("Message")) errorJson.get("Message") as? String else null
                val otherMessageError =
                    if (errorJson.has("message")) errorJson.get("message") as? String else null
                val secondMessageError =
                    if (errorJson.has("secondMessageError")) errorJson.get("secondMessageError") as? String else null
                val exceptionMessage =
                    if (errorJson.has("exceptionMessage")) errorJson.get("exceptionMessage") as? String else null

                messageError ?: otherMessageError ?: secondMessageError ?: exceptionMessage ?: ""
            } catch (exception: Exception) {
                null
            }
        }
    }

    fun sendErrorMessage(json: String?) {
        val errorMessage = getServerError(json)
        if (!errorMessage.isNullOrEmpty()) {
            RxBus.publish(RxEvent.EventMessage(errorMessage, Toast.LENGTH_LONG))
        }
    }
}