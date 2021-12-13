package company.vk.education.androidcourse.rememberthepills.components.api

import company.vk.education.androidcourse.rememberthepills.components.api.ApiModel.DauResponse
import company.vk.education.androidcourse.rememberthepills.components.api.ApiModel.DrugCreateAnalyticResponse
import company.vk.education.androidcourse.rememberthepills.components.api.ApiModel.ScheduleClickAnalyticRequest
import company.vk.education.androidcourse.rememberthepills.components.api.ApiModel.ScheduleClickAnalyticResponse
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