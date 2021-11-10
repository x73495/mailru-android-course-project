package com.airdron.mailcourse.fragments.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.airdron.mailcourse.R

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
            it.findNavController().navigate(R.id.action_fragmentSchedule_to_fragmentCourseEdit)
        }

        view.findViewById<Button>(R.id.button_to_course_add).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentSchedule_to_fragmentCourseAdd)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}