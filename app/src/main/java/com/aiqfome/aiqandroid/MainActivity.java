package com.aiqfome.aiqandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.aiqfome.aiqandroid.databinding.ActivityMainBinding;
import com.aiqfome.aiqinput.IconSubTitleItem;
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
    }

    private void setupTextInputCountryPhone() {
        countries.add(new Country(getResources().getDrawable(R.drawable.ic_flag_br),
                "+55", "Brazil"));

        countries.add(new Country(getResources().getDrawable(R.drawable.ic_flag_cl),
                "+56", "Chile"));

        List<IconSubTitleItem<Country>> countriesView = new ArrayList<>();

        for (Country c : countries)
            countriesView.add(new IconSubTitleItem<>(c, c.getName(), c.getIcon(), c.getIdd()));

        TextInputController countriesController = new TextInputController<Country>(
                getSupportFragmentManager(),
                "Countries",
                countriesView) {

            @Override
            public void onItemSelected(Country country) {
                Toast.makeText(MainActivity.this, country.getName(), Toast.LENGTH_SHORT)
                        .show();

                dismiss();
            }
        };

        binding.tiCountryPhone.setup(countriesController);
    }

    private void setupTextInputColorAndName() {

        List<IconSubTitleItem<Integer>> colorsView = new ArrayList<>();

        colorsView.add(new IconSubTitleItem<>(1, "Blue",
                getResources().getDrawable(R.drawable.ic_blue)));

        colorsView.add(new IconSubTitleItem<>(2, "Red",
                getResources().getDrawable(R.drawable.ic_red)));

        TextInputController colorsController = new TextInputController<Integer>(
                getSupportFragmentManager(),
                "Colors",
                colorsView) {

            @Override
            public void onItemSelected(Integer colorValue) {
                dismiss();
            }
        };

        binding.tiColorName.setup(colorsController);
    }
}
