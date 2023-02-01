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
import androidx.recyclerview.widget.LinearLayoutManager
import com.peter.carpark.R
import com.peter.carpark.adapter.CarParkInfoAdapter
import com.peter.carpark.databinding.FragmentCarkParkInfoBinding
import com.peter.carpark.viewmodel.CarParkInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CarParkInfoFragment : Fragment() {

    private val viewModel by viewModels<CarParkInfoViewModel>()
    private var _binding: FragmentCarkParkInfoBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var mCarParkInfoAdapter: CarParkInfoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCarkParkInfoBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerData()
        initView()
    }
    private fun initView(){
        val layoutManager = LinearLayoutManager(context)
        binding.carParkRv.layoutManager = layoutManager
        binding.carParkRv.adapter=mCarParkInfoAdapter
        binding.timeZoneBtn.setOnClickListener {
            Navigation.findNavController(binding.timeZoneBtn)
                .navigate(R.id.action_carkParkInfoFragment_to_timeZoneFragment)
        }
    }
    private fun observerData(){
        viewModel.getCarParkData()
        viewModel.errorMessage.observe(viewLifecycleOwner)  {error->
            Log.e("peter", "errorMessage$error")
            Toast.makeText(context,error, Toast.LENGTH_LONG).show()
        }
        viewModel.mCarParkInfoListLiveData.observe(viewLifecycleOwner) { carParkInfoList ->
            Log.e("peter", "carParkInfoList${carParkInfoList.size}")
            mCarParkInfoAdapter.setCarParkInfoList(carParkInfoList)
        }
    }

}