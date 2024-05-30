package com.example.shopping_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping_list.adapter.ShopAdapter
import com.example.shopping_list.data.DbManager
import com.example.shopping_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ShopAdapter(this)
    private val dbManager = DbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
            btnAdd.setOnClickListener {
                if (edEnterText.text.toString() != ""){
                    val insertText = InfoInList(edEnterText.text.toString())
                    dbManager.insertToDb(insertText)
                    val dataList = dbManager.readDb()
                    for (i in dataList){
                        adapter.addText(InfoInList(i))
                    }
                    updateRecyclerView()

                    edEnterText.setText("")
                }

            }
        }
    }

    private fun updateRecyclerView() {
        val dataList = dbManager.readDb().map { InfoInList(it) }
        adapter.setData(dataList)

    }

    override fun onResume() {
        super.onResume()
        dbManager.openDb()
       val dataList = dbManager.readDb()
        for (i in dataList){
            adapter.addText(InfoInList(i))
        }

        updateRecyclerView()

    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }
}