package company.vk.education.androidcourse.rememberthepills.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import company.vk.education.androidcourse.rememberthepills.R

class FragmentDrugList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drug_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button_to_drug_add).setOnClickListener {
            val action =
                FragmentDrugListDirections.actionFragmentDrugListToFragmentDrug(
                    "add"
                )
            it.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.button_to_drug_edit).setOnClickListener {
            val action =
                FragmentDrugListDirections.actionFragmentDrugListToFragmentDrug(
                    "edit"
                )
            it.findNavController().navigate(action)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}