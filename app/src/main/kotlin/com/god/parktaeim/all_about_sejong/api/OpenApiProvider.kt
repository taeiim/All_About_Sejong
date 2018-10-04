package com.god.parktaeim.all_about_sejong.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

fun api(): OpenApi = Retrofit.Builder()
        .baseUrl("http://xxxx.yyyy.com/")
        .client(OkHttpClient(provideLoggingInterceptor()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenApi::class.java)

fun sejongData(): OpenApi = Retrofit.Builder()
        .baseUrl("http://openapi.sejong.go.kr/openapi-data/service/")
        .client(OkHttpClient(provideLoggingInterceptor()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
        .build()
        .create(OpenApi::class.java)

private fun OkHttpClient(
        interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            build()
        }

private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
