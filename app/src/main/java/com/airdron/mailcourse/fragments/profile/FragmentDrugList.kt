package com.airdron.mailcourse.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.airdron.mailcourse.R

class FragmentDrugList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drug_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button_to_drug_add).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentDrugList_to_fragmentDrugAdd)
        }

        view.findViewById<Button>(R.id.button_to_drug_edit).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentDrugList_to_fragmentDrugEdit)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}