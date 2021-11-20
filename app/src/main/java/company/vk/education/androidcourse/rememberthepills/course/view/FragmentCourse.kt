package company.vk.education.androidcourse.rememberthepills.course.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.storage.models.FoodAddictionItem
import company.vk.education.androidcourse.rememberthepills.components.storage.models.MeasurementItem
import company.vk.education.androidcourse.rememberthepills.course.viewModel.CourseViewModel
import company.vk.education.androidcourse.rememberthepills.drug.view.adapter.IntakeTimeAdapter
import company.vk.education.androidcourse.rememberthepills.drug.model.IntakeTime
import java.text.SimpleDateFormat

class FragmentCourse : Fragment() {

    private val args: FragmentCourseArgs by navArgs()
    private val courseViewModel: CourseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemsMeasurements = MeasurementItem.values().map { getString(it.textId) }
        val adapterItemsMeasurement =
            ArrayAdapter(requireContext(), R.layout.item_text_view_drop_down_list, itemsMeasurements)
        (view.findViewById<AutoCompleteTextView>(R.id.input_text_course_measurement)).setAdapter(
            adapterItemsMeasurement
        )

        val itemsFoodDependency = FoodAddictionItem.values().map { getString(it.textId) }

        val adapterItemsFoodDependency =
            ArrayAdapter(requireContext(), R.layout.item_text_view_drop_down_list, itemsFoodDependency)
        (view.findViewById<AutoCompleteTextView>(R.id.input_text_course_food_dependency)).setAdapter(
            adapterItemsFoodDependency
        )

        when (args.action) {
            "add" -> {
                view.findViewById<TextView>(R.id.text_course_drug_name).text =
                    "Препарат с ID ${args.idOfDrug}"
                view.findViewById<TextView>(R.id.text_course_drug_type).text =
                    "Тип препарата с ID ${args.idOfDrug}"

                view.findViewById<Button>(R.id.button_course_remove).visibility = View.GONE
                view.findViewById<Button>(R.id.button_course_save).setText(R.string.add)
            }
            "edit" -> {
                view.findViewById<TextView>(R.id.text_course_drug_name).text =
                    "Препарат курса с ID ${args.idOfCourse}"
                view.findViewById<TextView>(R.id.text_course_drug_type).text =
                    "Тип препарата курса с ID ${args.idOfCourse}"

                view.findViewById<Button>(R.id.button_course_save).setText(R.string.save)
            }
        }

        // TODO TEMPORARY
        val intakeTimes = generateIntakeTimes().toMutableList()

        val recycler: RecyclerView = view.findViewById(R.id.recycler_time_medication)
        val adapter = IntakeTimeAdapter(intakeTimes, requireActivity())
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<Button>(R.id.button_course_add_time_medication).setOnClickListener {
            intakeTimes.add(IntakeTime(12, 0))
            adapter.notifyDataSetChanged()

            val scrollView = view.findViewById<ScrollView>(R.id.scroll_course)
            scrollView.post {
                scrollView.fullScroll(View.FOCUS_DOWN)
            }
        }

        view.findViewById<Button>(R.id.button_course_save).setOnClickListener {
            val action = FragmentCourseDirections.actionFragmentCourseToFragmentSchedule()
            it.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.button_course_remove).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.course_removal_confirmation_title))
                .setMessage(getString(R.string.course_removal_confirmation_message))
                .setNegativeButton(getString(R.string.removal_confirmation_answer_no)) { dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton(getString(R.string.removal_confirmation_answer_yes)) { dialog, which ->
                    // TODO: actual deletion
                    val action = FragmentCourseDirections.actionFragmentCourseToFragmentSchedule()
                    it.findNavController().navigate(action)
                }
                .show()
        }

        val inputCourseStart =
            view.findViewById<TextInputEditText>(R.id.input_edit_text_course_start)
        inputCourseStart.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    val datePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText(R.string.pick_course_start)
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()

                    datePicker.addOnPositiveButtonClickListener {
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
                        val datePicked = dateFormat.format(datePicker.selection)
                        inputCourseStart.setText(datePicked)
                    }

                    datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
                }
            }
            true
        }

        // TODO: get rid of ctrl+c ctrl+v
        val inputCourseEnd =
            view.findViewById<TextInputEditText>(R.id.input_edit_text_course_end)
        inputCourseEnd.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    val datePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText(R.string.pick_course_end)
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()

                    datePicker.addOnPositiveButtonClickListener {
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
                        val datePicked = dateFormat.format(datePicker.selection)
                        inputCourseEnd.setText(datePicked)
                    }

                    datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
                }
            }
            true
        }
    }

    // TODO TEMPORARY
    private fun generateIntakeTimes(): List<IntakeTime> {
        return listOf(
            IntakeTime(20, 21),
            IntakeTime(13, 37)
        )
    }
}