package company.vk.education.androidcourse.rememberthepills.analytics

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Analytics {

    private val baseURL = "https://1o12o.mocklab.io"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val retrofitAnalyticService: AnalyticsService = retrofit.create(AnalyticsService::class.java)
}
