package com.budgetku.app.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavOptions
import com.budgetku.app.R
import com.budgetku.app.databinding.FragmentRegisterBinding
import com.budgetku.app.util.Resource
import com.budgetku.app.viewmodel.AuthViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnRegister.setOnClickListener { register() }
        binding.tvLogin.setOnClickListener { findNavController().popBackStack() }
        viewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                Resource.Loading -> setLoading(true)
                is Resource.Success -> { setLoading(false); navigateToDashboard() }
                is Resource.Error -> { setLoading(false); Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show() }
            }
        }
    }

    private fun register() {
        val name = binding.etName.text?.toString()?.trim().orEmpty()
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val pass = binding.etPassword.text?.toString()?.trim().orEmpty()
        val confirm = binding.etConfirmPassword.text?.toString()?.trim().orEmpty()
        var ok = true
        if (name.length < 3) { binding.tilName.error = "Nama minimal 3 karakter"; ok = false } else binding.tilName.error = null
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { binding.tilEmail.error = "Email tidak valid"; ok = false } else binding.tilEmail.error = null
        if (pass.length < 6) { binding.tilPassword.error = "Password minimal 6 karakter"; ok = false } else binding.tilPassword.error = null
        if (pass != confirm) { binding.tilConfirmPassword.error = "Password tidak sama"; ok = false } else binding.tilConfirmPassword.error = null
        if (ok) viewModel.register(name, email, pass)
    }

    private fun setLoading(v: Boolean) {
        binding.progressBar.visibility = if (v) View.VISIBLE else View.GONE
        binding.btnRegister.isEnabled = !v
    }

    private fun navigateToDashboard() {
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph, true)
            .build()
        findNavController().navigate(R.id.dashboardFragment, null, options)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
