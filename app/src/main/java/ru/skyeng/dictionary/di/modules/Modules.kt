package ru.skyeng.dictionary.di.modules

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.skyeng.dictionary.data.api.SkyEngAPI
import ru.skyeng.dictionary.data.remote.DictionaryRepo
import ru.skyeng.dictionary.data.remote.DictionaryRepoImpl
import ru.skyeng.dictionary.ui.meaning.MeaningViewModel
import ru.skyeng.dictionary.ui.search.SearchViewModel
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

val appModules = module {
    single {
        createWebService<SkyEngAPI>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = BASE_URL
        )
    }
    factory<DictionaryRepo> { DictionaryRepoImpl(skyengAPI = get()) }

    viewModel { SearchViewModel(apiRepo = get()) }
    viewModel { MeaningViewModel(apiRepo = get()) }
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}


inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory, baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}