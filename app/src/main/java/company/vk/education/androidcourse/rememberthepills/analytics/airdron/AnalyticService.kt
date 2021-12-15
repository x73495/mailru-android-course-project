package company.vk.education.androidcourse.rememberthepills.analytics.airdron

import company.vk.education.androidcourse.rememberthepills.analytics.airdron.models.DauResponse
import company.vk.education.androidcourse.rememberthepills.analytics.airdron.models.DrugCreateAnalyticResponse
import company.vk.education.androidcourse.rememberthepills.analytics.airdron.models.ScheduleClickAnalyticRequest
import company.vk.education.androidcourse.rememberthepills.analytics.airdron.models.ScheduleClickAnalyticResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AnalyticService {

    @POST("analytics/dau")
    suspend fun sendDau(): DauResponse

    @POST("analytics/medicine/{id}")
    suspend fun sendDrugCreate(@Path("id") drugId: Long): DrugCreateAnalyticResponse

    @POST("analytics/schedule/click")
    suspend fun sendScheduleClick(@Body request: ScheduleClickAnalyticRequest): ScheduleClickAnalyticResponse
}