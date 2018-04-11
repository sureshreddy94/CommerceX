package com.kryptoblocks.commercex;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class MensClothingActivity extends AppCompatActivity {
    SliderLayout slider_men_clothing;
    ImageView img_denim_cat;
    Toolbar toolbar_clothing_men;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens_clothing);

        slider_men_clothing = findViewById(R.id.slider_men_clothing);
        img_denim_cat = findViewById(R.id.denim_category_mens);

        img_denim_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MensClothingActivity.this,SliderActivity.class);
                startActivity(i);
            }
        });

        //ImageSlider
        final HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("a", R.drawable.ad1);
        file_maps.put("b", R.drawable.ad3);
        file_maps.put("c", R.drawable.ad2);
        file_maps.put("d", R.drawable.ad4);
        file_maps.put("e", R.drawable.ad5);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getApplication());
            // initialize a SliderLayout
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    //.setOnSliderClickListener(this);
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {

                            Intent intent = new Intent(getApplication(), SliderActivity.class);
                            // intent.putExtra("imageurls", urls.get(finalI));
                            startActivity(intent);


                        }
                    });

            slider_men_clothing.addSlider(textSliderView);
        }


        slider_men_clothing.setPresetTransformer(SliderLayout.Transformer.Fade);
        slider_men_clothing.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider_men_clothing.setCustomAnimation(new DescriptionAnimation());
        slider_men_clothing.setDuration(6000);

        initToolBar();
        toolbar_clothing_men.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar_clothing_men.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MensClothingActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }
        );

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initToolBar() {
        toolbar_clothing_men = (Toolbar) findViewById(R.id.toolbar_mens_clothing);
        toolbar_clothing_men.setTitle(R.string.toolbar_for_men_cloth);
        toolbar_clothing_men.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar_clothing_men);
    }
}
