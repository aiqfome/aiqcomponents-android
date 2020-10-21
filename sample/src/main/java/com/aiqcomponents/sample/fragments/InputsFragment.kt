package com.aiqcomponents.sample.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.aiqcomponents.sample.R
import com.aiqcomponents.sample.models.Country
import com.aiqcomponents.sample.models.Region
import com.aiqfome.aiqcomponents.adapters.BaseItem
import com.aiqfome.aiqcomponents.adapters.IconItem
import com.aiqfome.aiqcomponents.selector.SelectorController
import com.aiqfome.aiqcomponents.textinput.TextInputController
import kotlinx.android.synthetic.main.fragment_inputs.*
import kotlinx.android.synthetic.main.fragment_inputs.view.*
import java.util.*

class InputsFragment : Fragment(R.layout.fragment_inputs) {

    var countries = mutableListOf<Country>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextInputCountryPhone()
        setupTextInputColorAndName()
        setupSelectorRegion()
        setupSelectorCity()
    }

    private fun setupTextInputCountryPhone() {
        countries.add(Country(VectorDrawableCompat.create(this.resources, R.drawable.ic_flag_br, activity?.theme)!!,
                "+55", "Brazil"))
        countries.add(Country(VectorDrawableCompat.create(this.resources, R.drawable.ic_flag_cl, activity?.theme)!!,
                "+56", "Chile"))
        val countriesView: MutableList<IconItem<Country>> = ArrayList()
        for (c in countries) countriesView.add(IconItem(c, c.name, c.idd, c.icon))
        val countriesController: TextInputController<*> = object : TextInputController<Country>(
                childFragmentManager,
                "Countries",
                countriesView,
                true,
                true) {
            override fun onItemSelected(country: Country) {
                Toast.makeText(context, country.name, Toast.LENGTH_SHORT)
                        .show()
            }
        }

        layoutInputsRoot.tiCountryPhone?.setup(countriesController)
    }

    private fun setupTextInputColorAndName() {
        val colorsView = mutableListOf<IconItem<Int?>>()
        colorsView.add(IconItem(1, "Blue", VectorDrawableCompat.create(this.resources, R.drawable.ic_blue, requireActivity().theme)))
        colorsView.add(IconItem(2, "Red", VectorDrawableCompat.create(this.resources, R.drawable.ic_red, requireActivity().theme)))
        val colorsController: TextInputController<*> = object : TextInputController<Int?>(
                childFragmentManager,
                "Colors",
                colorsView,
                false) {
            override fun onItemSelected(colorValue: Int?) {}
        }

        layoutInputsRoot.tiColorName?.setup(colorsController)
    }

    private fun setupSelectorRegion() {
        val regionList: MutableList<Region> = ArrayList()
        val regionViews: MutableList<BaseItem<Region>> = ArrayList()
        regionList.add(Region("Paraná", "PR"))
        regionList.add(Region("São Paulo", "SP"))
        regionList.add(Region("Mato Grosso", "MT"))
        for (r in regionList) regionViews.add(BaseItem(r, r.name, r.acronym))
        val selectorController: SelectorController<Region> = object : SelectorController<Region>(
                childFragmentManager,
                "Regions",
                regionViews,
                true,
                true,
                true,
                true
        ) {
            override fun onItemSelected(`object`: Region) {
                Toast.makeText(requireActivity(),
                        "Region: " + `object`.acronym,
                        Toast.LENGTH_SHORT).show()
            }
        }

        layoutInputsRoot.selectorRegion?.setup(selectorController)
    }

    private fun setupSelectorCity() {
        val regionList: MutableList<Region> = ArrayList()
        val cityViews: MutableList<BaseItem<Region>> = ArrayList()
        regionList.add(Region("Maringá", ""))
        regionList.add(Region("Curitiba", ""))
        regionList.add(Region("Rio de Janeiro", ""))
        for (r in regionList) cityViews.add(BaseItem(r, r.name, r.acronym))
        val selectorController: SelectorController<Region> = object : SelectorController<Region>(
                childFragmentManager,
                "Cities",
                cityViews,
                true) {
            override fun onItemSelected(`object`: Region) {
                Toast.makeText(requireActivity(),
                        "City: " + `object`.acronym,
                        Toast.LENGTH_SHORT).show()
            }
        }

        layoutInputsRoot.selectorCity?.setup(selectorController)
    }

}