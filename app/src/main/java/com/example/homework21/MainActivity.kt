package com.example.homework21

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework21.MultiType.Author
import com.example.homework21.MultiType.ButtonText
import com.example.homework21.MultiType.ImageText
import com.example.homework21.MultiType.MyMultiAdapter
import com.example.homework21.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listOfData = mutableListOf(Author(), ImageText(), ButtonText())
        val myAdapter = MyMultiAdapter(listOfData)
        binding.myRecycler.layoutManager = LinearLayoutManager(this)
        binding.myRecycler.adapter = myAdapter

        binding.buttonAdd.setOnClickListener {
            when (Random.nextInt(0, 3)) {
                0 -> listOfData.add(Author())
                1 -> listOfData.add(ButtonText())
                2 -> listOfData.add(ImageText())
            }
            Toast.makeText(this, "Added random card", Toast.LENGTH_SHORT).show()
        }

        binding.buttonUpdate.setOnClickListener {
            myAdapter.notifyDataSetChanged()
        }
    }


}