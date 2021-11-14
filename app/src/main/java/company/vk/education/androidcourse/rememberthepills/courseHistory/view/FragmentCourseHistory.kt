package company.vk.education.androidcourse.rememberthepills.courseHistory.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.R

class FragmentCourseHistory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_history, container, false)
    }
}