package com.aiqcomponents.sample.models

import androidx.navigation.findNavController
import com.aiqcomponents.sample.fragments.HomeFragmentDirections

object ComponentItems {
    val list =
            listOf(
                    ComponentItem(
                            "input components",
                            {
                                val action =
                                        HomeFragmentDirections.actionHomeFragmentToInputsFragment()
                                it.findNavController().navigate(action)
                            },
                            "some fun input components"
                    ),
            )
}