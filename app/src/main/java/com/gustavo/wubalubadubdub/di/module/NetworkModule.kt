package com.gustavo.wubalubadubdub.di.module

import com.gustavo.wubalubadubdub.BuildConfig
import com.gustavo.wubalubadubdub.source.remote.Api
import com.gustavo.wubalubadubdub.utils.BASE_URL
import com.gustavo.wubalubadubdub.utils.DateFormatUtil
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Provides
import dagger.Module
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.text.ParseException
import java.util.*

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
}