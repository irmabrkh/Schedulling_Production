package com.example.scheduling_production.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.scheduling_production.helper.DatabaseHelper

class TaskFragment : Fragment() {
    private lateinit var tableLayout: TableLayout
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tableLayout = findViewById(R.id.tableLayout)
        dbHelper = DatabaseHelper(this)

        loadTableData()
    }

    private fun loadTableData() {
        val dataList = dbHelper.getAllData()

        for (data in dataList) {
            val row = TableRow(this)

            val col1 = TextView(this).apply {
                text = data.column1
                setPadding(8, 8, 8, 8)
            }
            row.addView(col1)

            val col2 = TextView(this).apply {
                text = data.column2
                setPadding(8, 8, 8, 8)
            }
            row.addView(col2)

            tableLayout.addView(row)
        }
    }
}