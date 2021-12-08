package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CourseAndDrug(
    @Embedded
    val course: CourseEntity,
    @Relation(
        parentColumn = "drug_id",
        entityColumn = "id"
    )
    val drug: DrugEntity
)