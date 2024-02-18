package com.test.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.test.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    }



//    fun onClick(v: View){
//        if(v.id == R.id.addNote){
//            navigateToAddNotesScreen()
//        }
//    }
//    private fun navigateToAddNotesScreen(){
//        val intent = Intent(this, AddNote::class.java)
//        intent.putExtra("test", "testContent")
//        startActivity(intent)
//    }
}