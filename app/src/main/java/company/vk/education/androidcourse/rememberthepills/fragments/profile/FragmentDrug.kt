package company.vk.education.androidcourse.rememberthepills.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import company.vk.education.androidcourse.rememberthepills.R

class FragmentDrug : Fragment() {

    val args: FragmentDrugArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drug, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (args.action) {
            "add" -> view.findViewById<TextView>(R.id.p_text_drug_action).setText(R.string.add_drug)
            "edit" -> {
                view.findViewById<TextView>(R.id.p_text_drug_action).text = "${getString(R.string.edit_drug)} с ID ${args.idOfDrug}"
            }
        }
    }
}