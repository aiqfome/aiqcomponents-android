package com.aiqfome.aiqandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.aiqfome.aiqandroid.databinding.ActivityMainBinding;
import com.aiqfome.aiqinput.adapters.BaseItem;
import com.aiqfome.aiqinput.adapters.IconItem;
import com.aiqfome.aiqinput.selector.SelectorController;
import com.aiqfome.aiqinput.textinput.TextInputController;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Country> countries = new ArrayList<>();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupTextInputCountryPhone();
        setupTextInputColorAndName();
        setupSelectorRegion();
        setupSelectorCity();
    }

    private void setupTextInputCountryPhone() {
        countries.add(new Country(VectorDrawableCompat.
                create(this.getResources(), R.drawable.ic_flag_br, this.getTheme()),
                "+55", "Brazil"));

        countries.add(new Country(VectorDrawableCompat.
                create(this.getResources(), R.drawable.ic_flag_cl, this.getTheme()),
                "+56", "Chile"));

        List<IconItem<Country>> countriesView = new ArrayList<>();

        for (Country c : countries)
            countriesView.add(new IconItem<>(c, c.getName(), c.getIdd(), c.getIcon()));

        TextInputController countriesController = new TextInputController<Country>(
                getSupportFragmentManager(),
                "Countries",
                countriesView,
                true,
                true) {

            @Override
            public void onItemSelected(Country country) {
                Toast.makeText(MainActivity.this, country.getName(), Toast.LENGTH_SHORT)
                        .show();
            }
        };

        binding.tiCountryPhone.setup(countriesController);
    }

    private void setupTextInputColorAndName() {

        List<IconItem<Integer>> colorsView = new ArrayList<>();
        colorsView.add(new IconItem<>(1, "Blue", VectorDrawableCompat.
                create(this.getResources(), R.drawable.ic_blue, this.getTheme())));

        colorsView.add(new IconItem<>(2, "Red", VectorDrawableCompat.
                create(this.getResources(), R.drawable.ic_red, this.getTheme())));

        TextInputController colorsController = new TextInputController<Integer>(
                getSupportFragmentManager(),
                "Colors",
                colorsView,
                false) {

            @Override
            public void onItemSelected(Integer colorValue) { }
        };

        binding.tiColorName.setup(colorsController);
    }

    private void setupSelectorRegion() {

        List<Region> regionList = new ArrayList<>();
        List<BaseItem<Region>> regionViews = new ArrayList<>();

        regionList.add(new Region("Paraná", "PR"));
        regionList.add(new Region("São Paulo", "SP"));
        regionList.add(new Region("Mato Grosso", "MT"));

        for (Region r : regionList)
            regionViews.add(new BaseItem<>(r, r.getName(), r.getAcronym()));

        SelectorController<Region> selectorController = new SelectorController<Region>(
                getSupportFragmentManager(),
                "Regions",
                regionViews,
                true,
                true,
                true,
                true
                ) {

            @Override
            public void onItemSelected(Region object) {
                Toast.makeText(MainActivity.this,
                        "Region: " + object.getAcronym(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        binding.selectorRegion.setup(selectorController);
    }

    private void setupSelectorCity() {

        List<Region> regionList = new ArrayList<>();
        List<BaseItem<Region>> cityViews = new ArrayList<>();

        regionList.add(new Region("Maringá", ""));
        regionList.add(new Region("Curitiba", ""));
        regionList.add(new Region("Rio de Janeiro", ""));

        for (Region r : regionList)
            cityViews.add(new BaseItem<>(r, r.getName(), r.getAcronym()));

        SelectorController<Region> selectorController = new SelectorController<Region>(
                getSupportFragmentManager(),
                "Cities",
                cityViews,
                true) {

            @Override
            public void onItemSelected(Region object) {
                Toast.makeText(MainActivity.this,
                        "City: " + object.getAcronym(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        binding.selectorCity.setup(selectorController);
    }
}
