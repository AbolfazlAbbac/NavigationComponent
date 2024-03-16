package com.example.project.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.databinding.FragmentSplashBinding
import com.example.project.utils.CheckConnection
import com.example.project.utils.DataStoreUsers
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var dataStoreUsers: DataStoreUsers

    @Inject
    lateinit var checkConnection: CheckConnection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Create a delay
        checkConnection.observe(viewLifecycleOwner) {
            if (it) {
                lifecycle.coroutineScope.launch {
                    delay(2000)
                    //Read DataStore(Token)
                    dataStoreUsers.readDataStore().collect { token ->
                        if (token.isEmpty()) {
                            findNavController().navigate(R.id.splashToRegister)
                        } else {
                            findNavController().navigate(R.id.splashToHome)
                        }
                    }
                }
            } else {
                Snackbar.make(
                    view,
                    "Please Make sure Your internet is Available",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }


}