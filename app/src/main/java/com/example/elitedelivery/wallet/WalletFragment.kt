package com.example.elitedelivery.wallet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.FragmentWalletBinding
import com.example.elitedelivery.profile.WithDrawActivity


class WalletFragment : Fragment() {

    private lateinit var binding:FragmentWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWalletBinding.inflate(inflater,container,false)

        binding.withdrawLL.setOnClickListener {
            startActivity(Intent(activity,WithDrawActivity::class.java).putExtra("IntentFrom","WalletFragment"))
        }



        return binding.root
    }

}