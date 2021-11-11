package company.vk.education.androidcourse.rememberthepills.fragments.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import company.vk.education.androidcourse.rememberthepills.R

class FragmentSchedule : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button_to_course_edit).setOnClickListener {
            val action =
                FragmentScheduleDirections.actionFragmentScheduleToFragmentCourse(
                    "edit"
                )
            it.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.button_to_course_add).setOnClickListener {
            val action =
                FragmentScheduleDirections.actionFragmentScheduleToFragmentCourse(
                    "add"
                )
            it.findNavController().navigate(action)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}