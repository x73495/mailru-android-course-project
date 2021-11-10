package com.airdron.mailcourse.fragments.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.airdron.mailcourse.R

class FragmentCourse : Fragment() {

    val args: FragmentCourseArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (args.action) {
            "add" -> view.findViewById<TextView>(R.id.p_text_course_action).setText(R.string.add_course)
            "edit" -> view.findViewById<TextView>(R.id.p_text_course_action).setText(R.string.edit_course)
        }
    }
}