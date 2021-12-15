package company.vk.education.androidcourse.rememberthepills.analytics

import company.vk.education.androidcourse.rememberthepills.analytics.models.course.CourseAdditionRequest
import company.vk.education.androidcourse.rememberthepills.analytics.models.course.CourseAdditionResponse
import company.vk.education.androidcourse.rememberthepills.analytics.models.drug.DrugAdditionRequest
import company.vk.education.androidcourse.rememberthepills.analytics.models.drug.DrugAdditionResponse
import company.vk.education.androidcourse.rememberthepills.analytics.models.view.ViewResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AnalyticsService {

    @POST("drug")
    suspend fun postDrug(@Body request: DrugAdditionRequest): DrugAdditionResponse

    @POST("course")
    suspend fun postCourse(@Body request: CourseAdditionRequest): CourseAdditionResponse

    @GET("view")
    suspend fun getViewApp(): ViewResponse
}