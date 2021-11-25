package company.vk.education.androidcourse.rememberthepills.course.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseDiffCallback
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseRecyclerViewAdapter
import company.vk.education.androidcourse.rememberthepills.components.base.utils.DividerItemDecorationFactory
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.form.viewHolder.FormViewHolderFactory
import company.vk.education.androidcourse.rememberthepills.course.viewModel.CourseViewModel
import company.vk.education.androidcourse.rememberthepills.course.viewModel.CourseViewModelFactory
import company.vk.education.androidcourse.rememberthepills.databinding.FragmentCourseBinding

class FragmentCourse : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding: FragmentCourseBinding get() = _binding!!

    private val args: FragmentCourseArgs by navArgs()
    private val courseViewModel: CourseViewModel by viewModels() {
        CourseViewModelFactory(
            args.mode,
            courseId = args.courseId,
            drugId = args.drugId,
            ResourceProvider(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCourseBinding.inflate(inflater, container, false)
        _binding = binding

        val viewHolderFactory = FormViewHolderFactory()
        val diffUtilCallback = BaseDiffCallback()
        val adapter = BaseRecyclerViewAdapter(viewHolderFactory, diffUtilCallback)
        val dividerItemDecoratorFactory = DividerItemDecorationFactory()
        binding.courseFormRecyclerView.adapter = adapter
        binding.courseFormRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.courseFormRecyclerView.addItemDecoration(
            dividerItemDecoratorFactory.create(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        subscribeViewModel()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeViewModel() {
        val adapter = binding.courseFormRecyclerView.adapter as BaseRecyclerViewAdapter
        courseViewModel.presentationModel.observe(viewLifecycleOwner, {
            adapter.submitList(it.listItems)
        })
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val itemsMeasurements = MeasurementItem.values().map { getString(it.textId) }
//        val adapterItemsMeasurement =
//            ArrayAdapter(requireContext(), R.layout.item_text_view_drop_down_list, itemsMeasurements)
//        (view.findViewById<AutoCompleteTextView>(R.id.input_text_course_measurement)).setAdapter(
//            adapterItemsMeasurement
//        )
//
//        val itemsFoodDependency = FoodAddictionItem.values().map { getString(it.textId) }
//
//        val adapterItemsFoodDependency =
//            ArrayAdapter(requireContext(), R.layout.item_text_view_drop_down_list, itemsFoodDependency)
//        (view.findViewById<AutoCompleteTextView>(R.id.input_text_course_food_dependency)).setAdapter(
//            adapterItemsFoodDependency
//        )
//
//        when (args.action) {
//            "add" -> {
//                view.findViewById<TextView>(R.id.text_course_drug_name).text =
//                    "Препарат с ID ${args.idOfDrug}"
//                view.findViewById<TextView>(R.id.text_course_drug_type).text =
//                    "Тип препарата с ID ${args.idOfDrug}"
//
//                view.findViewById<Button>(R.id.button_course_remove).visibility = View.GONE
//                view.findViewById<Button>(R.id.button_course_save).setText(R.string.add)
//            }
//            "edit" -> {
//                view.findViewById<TextView>(R.id.text_course_drug_name).text =
//                    "Препарат курса с ID ${args.idOfCourse}"
//                view.findViewById<TextView>(R.id.text_course_drug_type).text =
//                    "Тип препарата курса с ID ${args.idOfCourse}"
//
//                view.findViewById<Button>(R.id.button_course_save).setText(R.string.save)
//            }
//        }
//
//        // TODO TEMPORARY
//        val intakeTimes = generateIntakeTimes().toMutableList()
//
//        val recycler: RecyclerView = view.findViewById(R.id.recycler_time_medication)
//        val adapter = IntakeTimeAdapter(intakeTimes, requireActivity())
//        recycler.adapter = adapter
//        recycler.layoutManager = LinearLayoutManager(requireContext())
//
//        view.findViewById<Button>(R.id.button_course_add_time_medication).setOnClickListener {
//            intakeTimes.add(IntakeTime(12, 0))
//            adapter.notifyDataSetChanged()
//
//            val scrollView = view.findViewById<ScrollView>(R.id.scroll_course)
//            scrollView.post {
//                scrollView.fullScroll(View.FOCUS_DOWN)
//            }
//        }
//
//        view.findViewById<Button>(R.id.button_course_save).setOnClickListener {
//            val action = FragmentCourseDirections.actionFragmentCourseToFragmentSchedule()
//            it.findNavController().navigate(action)
//        }
//
//        view.findViewById<Button>(R.id.button_course_remove).setOnClickListener {
//            MaterialAlertDialogBuilder(requireContext())
//                .setTitle(getString(R.string.course_removal_confirmation_title))
//                .setMessage(getString(R.string.course_removal_confirmation_message))
//                .setNegativeButton(getString(R.string.removal_confirmation_answer_no)) { dialog, which ->
//                    dialog.cancel()
//                }
//                .setPositiveButton(getString(R.string.removal_confirmation_answer_yes)) { dialog, which ->
//                    // TODO: actual deletion
//                    val action = FragmentCourseDirections.actionFragmentCourseToFragmentSchedule()
//                    it.findNavController().navigate(action)
//                }
//                .show()
//        }
//
//        val inputCourseStart =
//            view.findViewById<TextInputEditText>(R.id.input_edit_text_course_start)
//        inputCourseStart.setOnTouchListener { view, motionEvent ->
//            when (motionEvent.action) {
//                MotionEvent.ACTION_UP -> {
//                    val datePicker = MaterialDatePicker.Builder.datePicker()
//                        .setTitleText(R.string.pick_course_start)
//                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
//                        .build()
//
//                    datePicker.addOnPositiveButtonClickListener {
//                        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
//                        val datePicked = dateFormat.format(datePicker.selection)
//                        inputCourseStart.setText(datePicked)
//                    }
//
//                    datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
//                }
//            }
//            true
//        }
//
//        // TODO: get rid of ctrl+c ctrl+v
//        val inputCourseEnd =
//            view.findViewById<TextInputEditText>(R.id.input_edit_text_course_end)
//        inputCourseEnd.setOnTouchListener { view, motionEvent ->
//            when (motionEvent.action) {
//                MotionEvent.ACTION_UP -> {
//                    val datePicker = MaterialDatePicker.Builder.datePicker()
//                        .setTitleText(R.string.pick_course_end)
//                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
//                        .build()
//
//                    datePicker.addOnPositiveButtonClickListener {
//                        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
//                        val datePicked = dateFormat.format(datePicker.selection)
//                        inputCourseEnd.setText(datePicked)
//                    }
//
//                    datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
//                }
//            }
//            true
//        }
//    }
//
//    // TODO TEMPORARY
//    private fun generateIntakeTimes(): List<IntakeTime> {
//        return listOf(
//            IntakeTime(20, 21),
//            IntakeTime(13, 37)
//        )
//    }
}