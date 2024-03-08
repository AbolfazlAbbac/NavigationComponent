package com.example.navigationcomponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponent.databinding.ActivityMainBinding
import com.example.navigationcomponent.databinding.FragmentOneBinding


class FragmentOne : Fragment() {
    private lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnClick.setOnClickListener {
                val bundle = bundleOf(
                    "amount" to etOne.text.toString(),
                    "data" to "${etOne.text} f Abbasi"
                )
                findNavController().navigate(R.id.action_fragmentOne_to_fragmentTwo, bundle)
            }
        }
    }
}