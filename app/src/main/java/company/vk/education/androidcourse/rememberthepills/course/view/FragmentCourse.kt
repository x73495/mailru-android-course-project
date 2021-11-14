package company.vk.education.androidcourse.rememberthepills.course.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import company.vk.education.androidcourse.rememberthepills.R

class FragmentCourse : Fragment() {

    private val args: FragmentCourseArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course, container, false)
    }
}