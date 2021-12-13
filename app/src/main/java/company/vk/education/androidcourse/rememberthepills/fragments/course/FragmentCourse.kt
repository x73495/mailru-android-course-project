package company.vk.education.androidcourse.rememberthepills.fragments.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.models.intakeTime.IntakeTimeAdapter
import company.vk.education.androidcourse.rememberthepills.models.intakeTime.IntakeTime
import company.vk.education.androidcourse.rememberthepills.room.course.DBClientCourses
import company.vk.education.androidcourse.rememberthepills.room.course.EntityCourse
import company.vk.education.androidcourse.rememberthepills.room.drug.DBClientDrugs
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate

class FragmentCourse : Fragment() {

    private val args: FragmentCourseArgs by navArgs()
    lateinit var entityDrugOfCourse: EntityDrug
    lateinit var entityChosenCourse: EntityCourse
    private val intakeTimes: MutableList<IntakeTime> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemsMeasurement = listOf(
            getString(R.string.drug_type_unit_pieces),
            getString(R.string.drug_type_unit_milligrams),
            getString(R.string.drug_type_unit_milliliters),
            getString(R.string.drug_type_unit_units),
            getString(R.string.drug_type_unit_doses),
            getString(R.string.drug_type_unit_teaspoons),
            getString(R.string.drug_type_unit_tablespoons)
        )
        val adapterItemsMeasurement =
            ArrayAdapter(requireContext(), R.layout.list_item, itemsMeasurement)
        (view.findViewById<AutoCompleteTextView>(R.id.input_text_course_measurement)).setAdapter(
            adapterItemsMeasurement
        )

        val itemsFoodDependency = listOf(
            getString(R.string.food_dependency_before_meals),
            getString(R.string.food_dependency_after_meal),
            getString(R.string.food_dependency_while_eating),
            getString(R.string.food_dependency_no)
        )
        val adapterItemsFoodDependency =
            ArrayAdapter(requireContext(), R.layout.list_item, itemsFoodDependency)
        (view.findViewById<AutoCompleteTextView>(R.id.input_text_course_food_dependency)).setAdapter(
            adapterItemsFoodDependency
        )

        when (args.action) {
            "add" -> {
                val dbClient = DBClientDrugs(requireActivity())

                lifecycleScope.launch {
                    dbClient.getByID(args.idOfDrug)
                }
                entityDrugOfCourse = dbClient.awaitResult() as EntityDrug

                view.findViewById<TextView>(R.id.text_course_drug_name).text =
                    entityDrugOfCourse.name
                view.findViewById<TextView>(R.id.text_course_drug_type).text =
                    entityDrugOfCourse.type

                view.findViewById<Button>(R.id.button_course_remove).visibility = View.GONE
                view.findViewById<Button>(R.id.button_course_save).setText(R.string.add)
            }
            "edit" -> {
                val dbClientCourses = DBClientCourses(requireActivity())

                lifecycleScope.launch {
                    dbClientCourses.getByID(args.idOfCourse)
                }
                entityChosenCourse = dbClientCourses.awaitResult() as EntityCourse

                val dbClientDrugs = DBClientDrugs(requireActivity())
                lifecycleScope.launch {
                    dbClientDrugs.getByID(entityChosenCourse.drugID)
                }
                entityDrugOfCourse = dbClientDrugs.awaitResult() as EntityDrug

                view.findViewById<TextView>(R.id.text_course_drug_name).text =
                    entityDrugOfCourse.name
                view.findViewById<TextView>(R.id.text_course_drug_type).text =
                    entityDrugOfCourse.type
                view.findViewById<AutoCompleteTextView>(R.id.input_text_course_measurement)
                    .setText(convertMeasurement(entityChosenCourse.measurement, true), false)
                view.findViewById<TextInputEditText>(R.id.input_text_course_quantity)
                    .setText(entityChosenCourse.amount.toString())
                view.findViewById<AutoCompleteTextView>(R.id.input_text_course_food_dependency)
                    .setText(entityChosenCourse.foodDependency, false)
                view.findViewById<TextInputEditText>(R.id.input_edit_text_course_start)
                    .setText(entityChosenCourse.dateStart.toString().replace('-', '/'))
                view.findViewById<TextInputEditText>(R.id.input_edit_text_course_end)
                    .setText(entityChosenCourse.dateEnd.toString().replace('-', '/'))
                view.findViewById<TextInputEditText>(R.id.input_edit_text_course_frequency)
                    .setText(entityChosenCourse.frequencyInDays.toString())

                view.findViewById<Button>(R.id.button_course_save).setText(R.string.save)

                intakeTimes.addAll(entityChosenCourse.intakeTimes)
            }
        }

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
            // ensuring the right format since i had (seemingly random) problems with this
            // ...change with caution
            fun formatDateString(dateAsString: String): String {
                val formattedDateSplit: List<String> =
                    if ('/' in dateAsString) {
                        dateAsString.split('/')
                    } else {
                        dateAsString.split('-')
                    }

                return if (formattedDateSplit[0].toInt() < 32) {
                    "${formattedDateSplit[2]}-${formattedDateSplit[1]}-${formattedDateSplit[0]}"
                } else {
                    "${formattedDateSplit[0]}-${formattedDateSplit[1]}-${formattedDateSplit[2]}"
                }
            }

            val textInputDateStart: TextInputEditText =
                view.findViewById(R.id.input_edit_text_course_start)
            val dateStartAsString: String = formatDateString(textInputDateStart.text.toString())

            val textInputDateEnd: TextInputEditText =
                view.findViewById(R.id.input_edit_text_course_end)
            val dateEndAsString: String = formatDateString(textInputDateEnd.text.toString())

            val dbClient = DBClientCourses(requireActivity())

            val resultingEntityCourse = EntityCourse(
                entityDrugOfCourse.id,
                convertMeasurement(view.findViewById<AutoCompleteTextView>(R.id.input_text_course_measurement).text.toString(), false),
                view.findViewById<TextInputEditText>(R.id.input_text_course_quantity).text.toString()
                    .toInt(),
                view.findViewById<AutoCompleteTextView>(R.id.input_text_course_food_dependency).text.toString(),
                LocalDate.parse(dateStartAsString),
                LocalDate.parse(dateEndAsString),
                view.findViewById<TextInputEditText>(R.id.input_edit_text_course_frequency).text.toString()
                    .toInt(),
                intakeTimes
            )

            lifecycleScope.launch {
                when (args.action) {
                    "add" -> dbClient.insertAll(resultingEntityCourse)
                    "edit" -> {
                        resultingEntityCourse.id = args.idOfCourse
                        dbClient.update(resultingEntityCourse)
                    }
                }
            }
            dbClient.awaitResult()

            lifecycleScope.launch {
                dbClient.getAll()
            }

            it.findNavController()
                .navigate(FragmentCourseDirections.actionFragmentCourseToFragmentSchedule())
        }

        view.findViewById<Button>(R.id.button_course_remove).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.course_removal_confirmation_title))
                .setMessage(getString(R.string.course_removal_confirmation_message))
                .setNegativeButton(getString(R.string.removal_confirmation_answer_no)) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(getString(R.string.removal_confirmation_answer_yes)) { _, _ ->
                    val dbClient = DBClientCourses(requireActivity())
                    lifecycleScope.launch {
                        dbClient.deleteByID(args.idOfCourse)
                    }
                    dbClient.awaitResult()

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

        // TODO: get rid of ctrl+c ctrl+v (this applies to a huge portion of the project rn...)
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

    private fun convertMeasurement(measurement: String, isMeasurementShort: Boolean): String {
        val measurementValuesFullToShort = mapOf(
            getString(R.string.drug_type_unit_pieces) to getString(R.string.drug_type_unit_pieces_short),
            getString(R.string.drug_type_unit_milligrams) to getString(R.string.drug_type_unit_milligrams_short),
            getString(R.string.drug_type_unit_milliliters) to getString(R.string.drug_type_unit_milliliters_short),
            getString(R.string.drug_type_unit_units) to getString(R.string.drug_type_unit_units_short),
            getString(R.string.drug_type_unit_doses) to getString(R.string.drug_type_unit_doses_short),
            getString(R.string.drug_type_unit_teaspoons) to getString(R.string.drug_type_unit_teaspoons_short),
            getString(R.string.drug_type_unit_tablespoons) to getString(R.string.drug_type_unit_tablespoons_short)
        )

        return if (isMeasurementShort) {
            val reversedMap =
                measurementValuesFullToShort.entries.associateBy({ it.value }) { it.key }
            reversedMap[measurement]!!
        } else {
            measurementValuesFullToShort[measurement]!!
        }
    }
}