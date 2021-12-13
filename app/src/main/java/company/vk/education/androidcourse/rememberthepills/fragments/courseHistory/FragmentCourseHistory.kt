package company.vk.education.androidcourse.rememberthepills.fragments.courseHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import company.vk.education.androidcourse.rememberthepills.MainActivity
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.room.course.DBClientCourses
import company.vk.education.androidcourse.rememberthepills.room.course.EntityCourse
import kotlinx.coroutines.launch

class FragmentCourseHistory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbClient = DBClientCourses(requireActivity())
        lifecycleScope.launch {
            dbClient.getAll()
        }
        val entitiesOfCourses = dbClient.awaitResult() as List<EntityCourse>

        val recycler: RecyclerView = view.findViewById(R.id.recycler_course_history)
        val adapter = CourseHistoryListAdapter(entitiesOfCourses, requireActivity() as MainActivity)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}
