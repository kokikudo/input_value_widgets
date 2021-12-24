package com.example.input_value_widgets

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.input_value_widgets.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // CheckBoxイベントリスナー
        // 第一引数　CheckBoxの基底クラスCompoundButton
        // 第二引数　チェックのOn/OFF
        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(
                this@MainActivity,
                if (isChecked) "Check Box On" else "Check Box OFF",
                Toast.LENGTH_SHORT
            ).show()
        }

        // ToggleButtonのイベントリスナー
        binding.toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(
                this@MainActivity,
                if (isChecked) "Toggle Button On" else "Toggle Button OFF",
                Toast.LENGTH_SHORT
            ).show()
        }

        // RadioButton
        // RadioGropeのリスナーを設定する（ラジオボタンが一つだけなのはあり得ないため）
        // 第一引数　RadioGroupオブジェクト
        // 第二引数　選択したRadioButtonのID
        binding.radioGrope.setOnCheckedChangeListener { group, checkedId ->
            // IDからオブジェクトを検索する
            val radio = group.findViewById<RadioButton>(checkedId)
            Toast.makeText(
                this@MainActivity,
                String.format("You choice %s", radio.text),
                Toast.LENGTH_SHORT
            ).show()
        }

        // SeekBar
        binding.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val str: String = getString(R.string.seek_bar_text, progress.toString())
                    binding.seekBarText.text = str
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        // Rating Star
        // contentDescriptionを定義しないとエラーになる
        // 基本的にはratingと同じ値で良い
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingBar.contentDescription = rating.toString() // 更新
            Log.d("NowRating", ratingBar.rating.toString())
        }
    }
}