package company.vk.education.androidcourse.rememberthepills.fragments.schedule

import android.os.Bundle
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import company.vk.education.androidcourse.rememberthepills.MainActivity
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.room.course.DBClientCourses
import company.vk.education.androidcourse.rememberthepills.room.course.EntityCourse
import company.vk.education.androidcourse.rememberthepills.room.drug.DBClientDrugs
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug
import company.vk.education.androidcourse.rememberthepills.room.entryForToday.DBClientEntryForToday
import company.vk.education.androidcourse.rememberthepills.room.entryForToday.EntityEntryForToday
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import java.util.*

class FragmentSchedule : Fragment() {

    lateinit var adapterForRecycler: CourseListAdapter
    private var courseEntries: MutableList<CourseEntry> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val rootView: ViewGroup = requireActivity().findViewById(R.id.root_layout)
//        val transition = Slide(Gravity.BOTTOM)
//        transition.addTarget(R.id.bottom_navigation_view)
//        transition.duration = 600
//        TransitionManager.beginDelayedTransition(rootView, transition)
//        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view).visibility = View.VISIBLE

        val fab = view.findViewById<FloatingActionButton>(R.id.button_to_course_add)
        fab.setOnClickListener {
//            fun toggle() {
//                val rootView: ViewGroup = requireActivity().findViewById(R.id.root_layout)
//                val transition = Slide(Gravity.BOTTOM)
//                transition.addTarget(R.id.bottom_navigation_view)
//                transition.duration = 600
//                TransitionManager.beginDelayedTransition(rootView, transition)
//                requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view).visibility = View.GONE
//            }
//
//            toggle()

            it.findNavController()
                .navigate(
                    FragmentScheduleDirections.actionFragmentScheduleToFragmentDrugList(
                        "choose"
                    )
                )
        }

        val localDateNow = LocalDate.now()
        getCoursesByDate(localDateNow)

        // TODO TEMPORARY
        val dbClientEntryForToday = DBClientEntryForToday(requireActivity())
        lifecycleScope.launch {
            dbClientEntryForToday.deleteEverything()
        }
        dbClientEntryForToday.awaitResult()

        lifecycleScope.launch {
            dbClientEntryForToday.insertAll(*courseEntries.map {
                EntityEntryForToday(it.intakeTime, it.idOfCourse, it.isDone, it.isMissed)
            }.toTypedArray())
        }
        dbClientEntryForToday.awaitResult()

        val recycler: RecyclerView = view.findViewById(R.id.recycler_course_entries_by_date)
        adapterForRecycler = CourseListAdapter(courseEntries, requireActivity() as MainActivity)
        recycler.adapter = adapterForRecycler
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val tempCalendar = Calendar.getInstance()
        tempCalendar.set(Calendar.HOUR_OF_DAY, 0)
        view.findViewById<TextView>(R.id.text_schedule_date_d).text =
            SimpleDateFormat("dd").format(tempCalendar.time)
        view.findViewById<TextView>(R.id.text_schedule_date_m).text =
            SimpleDateFormat("MMMM").format(tempCalendar.time)
        view.findViewById<TextView>(R.id.text_schedule_date_y).text =
            SimpleDateFormat("yyyy").format(tempCalendar.time)

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) fab.hide()
                else if (dy < 0) fab.show()
            }
        })

        view.findViewById<Button>(R.id.button_schedule_select_date).setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            datePicker.addOnPositiveButtonClickListener {
                val dateFormat = SimpleDateFormat("dd/MMMM/yyyy")
                val datePicked = dateFormat.format(datePicker.selection)
                val datePickedSplit = datePicked.split('/')

                val dateFormatForAdapter = SimpleDateFormat("yyyy-MM-dd")
                getCoursesByDate(LocalDate.parse(dateFormatForAdapter.format(datePicker.selection)))

                var textDay = datePickedSplit[0]
                if (textDay[0] == '0') {
                    textDay = datePickedSplit[0][1].toString()
                }

                view.findViewById<TextView>(R.id.text_schedule_date_d).text = textDay
                view.findViewById<TextView>(R.id.text_schedule_date_m).text = datePickedSplit[1]
                view.findViewById<TextView>(R.id.text_schedule_date_y).text = datePickedSplit[2]
            }

            datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
        }
    }

    private fun generateIntakeDays(entityCourse: EntityCourse): List<LocalDate> {
        val periodInDays = Period.between(entityCourse.dateStart, entityCourse.dateEnd).days

        val dates: MutableList<LocalDate> = mutableListOf()
        var tempDate = entityCourse.dateStart
        repeat(periodInDays) {
            dates.add(tempDate)
            tempDate = tempDate.plusDays(1)
        }
        dates.add(tempDate)

        val selectedDates: MutableList<LocalDate> = mutableListOf(dates[0])

        for (n in 1..(periodInDays / entityCourse.frequencyInDays)) {
            selectedDates.add(dates[entityCourse.frequencyInDays + ((n - 1) * entityCourse.frequencyInDays)])
        }

        return selectedDates
    }

    private fun getCoursesByDate(date: LocalDate) {
        val dbClientCourses = DBClientCourses(requireActivity())
        lifecycleScope.launch {
            dbClientCourses.getAll()
        }

        courseEntries.clear()
        if (this::adapterForRecycler.isInitialized) {
            adapterForRecycler.notifyDataSetChanged()
        }

        courseEntries.addAll((dbClientCourses.awaitResult() as MutableList<EntityCourse>).filter {
            (date.isBefore(it.dateEnd.plusDays(1))) and
                    (date.isAfter(it.dateStart.minusDays(1)) and
                            (date in generateIntakeDays(it)))
        }.map { entityCourse ->
            val dbClientDrugs = DBClientDrugs(requireActivity())
            lifecycleScope.launch {
                dbClientDrugs.getByID(entityCourse.drugID)
            }
            val entityDrugOfCourse: EntityDrug = dbClientDrugs.awaitResult() as EntityDrug

            entityCourse.intakeTimes.map {
                if (date == LocalDate.now()) {
                    val dbClientEntryForToday = DBClientEntryForToday(requireActivity())
                    lifecycleScope.launch {
                        dbClientEntryForToday.getByIntakeTime(it, entityCourse.id)
                    }
                    val tempEntityEntryForToday = dbClientEntryForToday.awaitResult()

                    val entryForTodayTarget: EntityEntryForToday =
                        if (tempEntityEntryForToday == false) {
                            EntityEntryForToday(
                                it,
                                123,
                                false,
                                LocalTime.now().isAfter(LocalTime.parse(it.toString()))
                            )
                        } else {
                            tempEntityEntryForToday as EntityEntryForToday
                        }

                    CourseEntry(
                        entityDrugOfCourse.name,
                        entityCourse.amount,
                        entryForTodayTarget.isDone,
                        entryForTodayTarget.isMissed,
                        entityCourse.id,
                        entityCourse.measurement,
                        it,
                        true,
                        entityCourse.foodDependency
                    )
                } else {
                    CourseEntry(
                        entityDrugOfCourse.name,
                        entityCourse.amount,
                        false,
                        false,
                        entityCourse.id,
                        entityCourse.measurement,
                        it,
                        false,
                        entityCourse.foodDependency
                    )
                }
            }
        }.flatten())

        if (this::adapterForRecycler.isInitialized) {
            adapterForRecycler.notifyDataSetChanged()
        }
    }
}