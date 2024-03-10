package com.example.project.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.project.R
import com.example.project.databinding.FragmentRegisterBinding
import com.example.project.models.BodyRegister
import com.example.project.utils.DataStoreUsers
import com.example.project.utils.isShown
import com.example.project.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var dataStore: DataStoreUsers

    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var body: BodyRegister
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            validationBoxes()


            submitBtn.setOnClickListener {
                validationBoxes()

                if (submitForm()) {
                    val name = nameEt.text.toString()
                    val email = emailEt.text.toString()
                    val password = passwordEt.text.toString()

                    body.email = email
                    body.name = name
                    body.password = password


                    viewModel.registerUser(body)

                    viewModel.loading.observe(viewLifecycleOwner) { isShown ->
                        if (isShown) {
                            loadingRegisterSubmit.isShown(true)
                            submitBtn.isClickable = false
                            submitBtn.text = ""
                        } else {
                            loadingRegisterSubmit.isShown(false)
                            submitBtn.isClickable = true
                            submitBtn.text = getString(R.string.submit)
                        }
                    }
                    viewModel.registerUser.observe(viewLifecycleOwner) { response ->
                        lifecycle.coroutineScope.launch {
                            dataStore.saveTokenDataStore(response.name)
                        }
                    }
                } else {
                    Snackbar.make(it, "Fill all the boxes correctly", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun nameFocusListener() {
        binding.nameEt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.nameInput.error = vailName()
            }
        }
    }

    private fun vailName(): String? {
        val nameText = binding.nameEt.text.toString()
        if (nameText.contains("[0-9]".toRegex())) {
            return "You Cannot use number in your name"
        }

        if (nameText.isEmpty()) {
            return "Please fill the box"
        }
        return null
    }

    private fun emailFocusListener() {
        binding.emailEt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.emailInput.error = vailEmail()
            }
        }
    }

    private fun vailEmail(): String? {
        val emailText = binding.emailEt.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Your email address is Invalid"
        }
        if (emailText.isEmpty()) {
            return "Please fill the box"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.passwordEt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.passwordInput.error = vailPassword()
            }
        }
    }

    private fun vailPassword(): String? {
        val passwordText = binding.passwordEt.text.toString()
        if (!passwordText.contains("[0-9]".toRegex())) {
            return "Use a Numbers in Password"

        }
        if (passwordText.isEmpty()) {
            return "Please fill the box"
        }

        if (!passwordText.contains("[A-Z]".toRegex())) {
            return "Use a Capital letter in Password"
        }

        if (!passwordText.contains("[a-z]".toRegex())) {
            return "Use a lower case letter in Password"
        }
        return null
    }

    private fun submitForm(): Boolean {
        val validName = binding.nameInput.error == null
        val isEmptyName = binding.nameEt.text?.isNotEmpty() == true
        val validEmail = binding.emailInput.error == null
        val isEmptyEmail = binding.emailEt.text?.isNotEmpty() == true
        val validPassword = binding.passwordInput.error == null
        val isEmptyPassword = binding.passwordEt.text?.isNotEmpty() == true

        return validName && validEmail && validPassword && isEmptyEmail && isEmptyPassword && isEmptyName
    }

    private fun validationBoxes() {
        emailFocusListener()
        nameFocusListener()
        passwordFocusListener()
    }
}