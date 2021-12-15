package company.vk.education.androidcourse.rememberthepills.analytics.airdron

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RTPApiDependency {

    //https://app.mocklab.io/mock-apis/d10qj/stubs/a3318ac7-e850-4f24-a03d-92210654d242
    private val BASE_URL = "https://d10qj.mocklab.io/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitAnalyticService : AnalyticService by lazy { retrofit.create(AnalyticService::class.java) }
}