package com.example.severalactivities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.severalactivities.databinding.ActivityActiv2Binding

class activ2 : AppCompatActivity() {
    private lateinit var binding: ActivityActiv2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityActiv2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //получаем список аргументов, присланных из первого активити
        //и если эти аргументы не null, то обрабатываем
        val arguments = intent.extras
        var name: String = "default"
        var age: Int = 1
        val eat = binding.eat.text
        if (arguments != null) {
            //метод get позволяет получить значение по ключу
            name = arguments.get("name").toString()
            age = arguments.get("age").toString().toInt()
        }
        binding.textView2.text = binding.textView2.text.toString() + ": " + name
        binding.textView3.text = binding.textView3.text.toString() + ": " + age

        val btn_toact1 = binding.buttonToact1
        btn_toact1.setOnClickListener {
            val act1_start = Intent(this, MainActivity::class.java)
            startActivity(act1_start)
        }

        val image_default = binding.imageDefault
        val btn_feed = binding.buttonFeed
        val btn_walk = binding.buttonWalk
        val btn_game = binding.buttonGame
        val btn_sleep = binding.buttonSleep
        btn_sleep.setEnabled(false)
        btn_feed.setEnabled(false)

        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.animaton)

        btn_walk.setOnClickListener {
            image_default.setImageResource(R.drawable.cat_hungry)
            binding.message.text = "Ваш питомец проголодался"
            btn_walk.setEnabled(false)
            btn_feed.setEnabled(true)
        }


        btn_feed.setOnClickListener {
            if (eat.toString().toInt() > 5) {
                image_default.setImageResource(R.drawable.cat_bl)
                binding.message.text = "Переел и ему нужно отдохнуть"
                btn_feed.setEnabled(false)
                btn_walk.setEnabled(false)
                btn_game.setEnabled(false)
                btn_sleep.setEnabled(true)
            } else {
                image_default.setImageResource(R.drawable.cat)
                binding.message.text = "Ваш питомец съел " + eat
                btn_feed.setEnabled(true)
                btn_walk.setEnabled(true)
            }
        }

        btn_sleep.setOnClickListener {
            image_default.setImageResource(R.drawable.cat)
            binding.message.text = "Ваш питомец полон сил"
            btn_sleep.setEnabled(false)
            btn_feed.setEnabled(true)
            btn_walk.setEnabled(true)
            btn_game.setEnabled(true)
        }


        btn_game.setOnClickListener {
            image_default.setImageResource(R.drawable.cat)
            binding.message.text = "Вы хороший хозяин"
            image_default.startAnimation(animation)
        }
    }
}