package org.hakandindis.movieapp.scene.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.hakandindis.movieapp.R
import org.hakandindis.movieapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MaterialAlertDialogBuilder(
            requireContext(),
            com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered
        )
            .setTitle("a")
            .setMessage("djDJadjaJAdja")
            .setIcon(resources.getDrawable(R.drawable.baseline_delete_outline_24))
            .setPositiveButton("yes") { a, b -> }
            .setNegativeButton("no") { a, b -> }
            .show()

    }
}