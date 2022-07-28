package com.aiqcomponents.sample.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.aiqcomponents.sample.R
import com.aiqcomponents.sample.databinding.FragmentInputsBinding
import com.aiqcomponents.sample.models.Country
import com.aiqcomponents.sample.models.Region
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.selector.SelectorController
import com.aiqfome.aiqcomponents.textinput.TextInputController
import java.util.*

class InputsFragment : Fragment(R.layout.fragment_inputs) {

    private var _binding: FragmentInputsBinding? = null
    private val binding: FragmentInputsBinding get() = _binding!!

    private var countries = mutableListOf<Country>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentInputsBinding.bind(view)

        setupTextInputCountryPhone()
        setupTextInputColorAndName()
        setupSelectorRegion()
        setupSelectorCity()
    }

    private fun setupTextInputCountryPhone() {
        countries.add(
            Country(
                VectorDrawableCompat.create(
                    this.resources,
                    R.drawable.ic_flag_br,
                    activity?.theme
                )!!,
                "+55", "Brazil"
            )
        )
        countries.add(
            Country(
                VectorDrawableCompat.create(
                    this.resources,
                    R.drawable.ic_flag_cl,
                    activity?.theme
                )!!,
                "+56", "Chile"
            )
        )

        val countriesView: MutableList<Item<Country>> = ArrayList()
        for (c in countries) countriesView.add(Item.Icon(c, c.name, c.idd, c.icon))
        val countriesController: TextInputController<*> = object : TextInputController<Country>(
            childFragmentManager,
            "Countries",
            countriesView,
            true,
            true
        ) {
            override fun onItemSelected(item: Country) {
                Toast.makeText(context, item.name, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.tiCountryPhone.setup(countriesController)
    }

    private fun setupTextInputColorAndName() {
        val colorsView = mutableListOf<Item.Icon<Int?>>()
        colorsView.add(
            Item.Icon(
                item = 1,
                title = "Blue",
                icon = VectorDrawableCompat.create(
                    this.resources,
                    R.drawable.ic_blue,
                    requireActivity().theme
                )
            )
        )
        colorsView.add(
            Item.Icon(
                item = 2,
                title = "Red",
                icon = VectorDrawableCompat.create(
                    this.resources,
                    R.drawable.ic_red,
                    requireActivity().theme
                )
            )
        )
        val colorsController: TextInputController<*> = object : TextInputController<Int?>(
            childFragmentManager,
            "Colors",
            colorsView as List<Item<Int?>>,
            false
        ) {
            override fun onItemSelected(item: Int?) {}
        }

        binding.tiColorName.setup(colorsController)
    }

    private fun setupSelectorRegion() {
        val regionList: MutableList<Region> = ArrayList()
        val regionViews: MutableList<Item.Text<Region>> = ArrayList()

        regionList.add(Region("Paraná", "PR"))
        regionList.add(Region("São Paulo", "SP"))
        regionList.add(Region("Mato Grosso", "MT"))

        regionList.forEach { region ->
            regionViews.add(
                Item.Text(region, region.name, region.acronym)
            )
        }

        val selectorController: SelectorController<Region> = object : SelectorController<Region>(
            childFragmentManager,
            "Regions",
            regionViews.toList(),
            true,
            true,
            true,
            true
        ) {
            override fun onItemSelected(item: Region) {
                Toast.makeText(
                    requireActivity(),
                    "Region: " + item.acronym,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.selectorRegion.setup(selectorController)
    }

    private fun setupSelectorCity() {
        val regionList: MutableList<Region> = ArrayList()
        val cityViews: MutableList<Item.Text<Region>> = ArrayList()

        regionList.add(Region("Maringá", ""))
        regionList.add(Region("Curitiba", ""))
        regionList.add(Region("Rio de Janeiro", ""))

        regionList.forEach { r ->
            cityViews.add(
                Item.Text(r, r.name, r.acronym)
            )
        }

        val selectorController: SelectorController<Region> = object : SelectorController<Region>(
            childFragmentManager,
            "Cities",
            cityViews,
            true
        ) {
            override fun onItemSelected(item: Region) {
                Toast.makeText(
                    requireActivity(),
                    "City: " + item.name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.selectorCity.setup(selectorController)
    }

}