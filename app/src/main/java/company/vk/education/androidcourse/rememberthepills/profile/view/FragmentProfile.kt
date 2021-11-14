package company.vk.education.androidcourse.rememberthepills.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import company.vk.education.androidcourse.rememberthepills.R

class FragmentProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button_to_drug_list).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentProfile_to_fragmentDrugList)
        }

        view.findViewById<Button>(R.id.button_to_course_history).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentProfile_to_fragmentCourseHistory)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}