package com.peter.carpark.fragment

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.peter.carpark.databinding.FragmentTimeZoneBinding
import com.peter.carpark.util.SharedPreferencesTools
import com.peter.carpark.viewmodel.TimeZoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class TimeZoneFragment : Fragment(), AdapterView.OnItemSelectedListener{

    private val viewModel by viewModels<TimeZoneViewModel>()
    private var _binding: FragmentTimeZoneBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTimeZoneBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerData()
        initView()
    }
    private fun initView(){
        val dinnerList= ArrayAdapter<Any?>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item, TimeZone.getAvailableIDs()
        )
        binding.timeZoneSpinner.adapter = dinnerList
        binding.timeZoneSpinner.setSelection(0, true);
        binding.timeZoneSpinner.onItemSelectedListener=this
        binding.emailTx.text = SharedPreferencesTools.getUserEmail(requireContext())
        binding.backBtn.setOnClickListener { view->
            Navigation.findNavController(view).popBackStack()
        }

    }
    private fun observerData(){
        viewModel.errorMessage.observe(viewLifecycleOwner)  {error->
            Log.e("peter", "errorMessage$error")
            Toast.makeText(context,error, Toast.LENGTH_LONG).show()
        }
        viewModel.mUpdateTimeZoneLiveData.observe(viewLifecycleOwner) { carParkInfoList ->
            Toast.makeText(context,"更新成功", Toast.LENGTH_LONG).show()
            Log.e("peter", "carParkInfoList${carParkInfoList}")
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val timeZoneId: String = parent?.getItemAtPosition(position).toString()
        if(SharedPreferencesTools.getUserSession(requireContext())!=null&&SharedPreferencesTools.getUserObjectId(requireContext())!=null){
            viewModel.updateTimeZone(SharedPreferencesTools.getUserSession(requireContext())!!
                , SharedPreferencesTools.getUserObjectId(requireContext())!!,timeZoneId)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}