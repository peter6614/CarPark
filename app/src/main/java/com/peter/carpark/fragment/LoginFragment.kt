package com.peter.carpark.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.peter.carpark.R
import com.peter.carpark.databinding.FragmentLoginBinding
import com.peter.carpark.util.SharedPreferencesTools
import com.peter.carpark.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
//            Navigation.findNavController(binding.loginBtn).navigate(R.id.action_loginFragment_to_carkParkInfoFragment)
            viewModel.login(binding.accountEt.text.toString(),binding.passwordEt.text.toString())
        }
        observerData()
    }
    private fun observerData(){
        viewModel.errorMessage.observe(viewLifecycleOwner)  {error->
            Log.e("peter", "errorMessage$error")
            Toast.makeText(context,error,Toast.LENGTH_LONG).show()
        }
        viewModel.mLoginLiveData.observe(viewLifecycleOwner) { user ->
            user.let {user->
                Log.e("peter", "login${user.name}")
                Log.e("peter", "login${user.sessionToken}")
                Log.e("peter", "login${user.timezone}")
                SharedPreferencesTools.setUserEmail(requireContext(),user.name)
                SharedPreferencesTools.setUserObjectId(requireContext(),user.objectId)
                Navigation.findNavController(binding.loginBtn).navigate(R.id.action_loginFragment_to_carkParkInfoFragment)
            }.takeIf { user.sessionToken.isNotEmpty() }.let {
                SharedPreferencesTools.setUserSession(requireContext(),user.sessionToken)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}