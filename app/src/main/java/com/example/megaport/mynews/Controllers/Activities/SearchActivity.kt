package com.example.megaport.mynews.Controllers.Activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.megaport.mynews.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.search_checkboxes.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

class SearchActivity : AppCompatActivity() {

    private var dateListener: DatePickerDialog.OnDateSetListener? = null
    private var dateListener2: DatePickerDialog.OnDateSetListener? = null
    private val cb = ArrayList<CheckBox>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        image_button_search_return.setOnClickListener { view -> startActivity() }

        // Setting action when the startDate EditText is focused
        search_begin_date.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // Setting up a new Calendar object in order to retrieve the date of the day
                val cal = Calendar.getInstance()
                val year = cal.get(Calendar.YEAR)
                val month = cal.get(Calendar.MONTH)
                val day = cal.get(Calendar.DAY_OF_MONTH)

                // Will create a new DatePickerDialog object with the date of the day
                val dialog = DatePickerDialog(
                        this@SearchActivity,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener,
                        year,
                        month,
                        day)
                // Set DatePickerDialog background transparent
                (dialog.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
            }
        }

        // Same as setOnFocusChangeListener but this time when the EditText has already been modified
        search_begin_date.setOnClickListener { v ->
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                    this@SearchActivity,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateListener,
                    year,
                    month,
                    day)
            (dialog.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        search_end_date.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                val cal = Calendar.getInstance()
                val year = cal.get(Calendar.YEAR)
                val month = cal.get(Calendar.MONTH)
                val day = cal.get(Calendar.DAY_OF_MONTH)

                val dialog = DatePickerDialog(
                        this@SearchActivity,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener2,
                        year,
                        month,
                        day)
                (dialog.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
            }
        }

        search_end_date.setOnClickListener { v ->
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                    this@SearchActivity,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateListener2,
                    year,
                    month,
                    day)
            (dialog.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        // Will update the concerned EditText with the date value chosen by the user
        dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            var monthS = Integer.toString(month + 1)
            var dayS = Integer.toString(dayOfMonth)
            if (month < 10) {
                monthS = "0$monthS"
            }
            if (dayOfMonth < 10) {
                dayS = "0$dayS"
            }
            search_begin_date.setText("$dayS/$monthS/$year")
        }

        dateListener2 = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            var monthS = Integer.toString(month + 1)
            var dayS = Integer.toString(dayOfMonth)
            if (month < 10) {
                monthS = "0$monthS"
            }
            if (dayOfMonth < 10) {
                dayS = "0$dayS"
            }
            search_end_date.setText("$dayS/$monthS/$year")
        }

        val inputPattern = "dd/MM/yyyy"
        val outputPattern = "yyyyMMdd"
        @SuppressLint("SimpleDateFormat") val inputFormat = SimpleDateFormat(inputPattern)
        @SuppressLint("SimpleDateFormat") val outputFormat = SimpleDateFormat(outputPattern)

        val searchButton = findViewById<Button>(R.id.search_submit_button)
        searchButton.setOnClickListener { view ->
            cb.clear()
            var section = "type_of_material:News"

            var eDate = ""
            var sDate = ""
            var date: Date?

            cb.add(search_cb_arts)
            cb.add(search_cb_business)
            cb.add(search_cb_politics)
            cb.add(search_cb_sports)
            cb.add(search_cb_entrepreneurs)
            cb.add(search_cb_travel)

            var count = 0

            for (i in cb.indices) {
                if (cb[i].isChecked) {
                    if (count == 0) {
                        section = section + " AND news_desk:(" + cb[i].text.toString()
                        count++
                    } else if (count > 0) {
                        section = section + " OR " + cb[i].text.toString()
                        count++
                    }
                }
            }

            if (count > 0)
                section = "$section)"

            if (count == 0) {
                Toast.makeText(this, getString(R.string.specifyAtLeastOneCategory), Toast.LENGTH_LONG).show()
            } else if ((query_include.text).toString().isEmpty() || query_include.text?.toString()?.trim { it <= ' ' }?.length == 0) {
                Toast.makeText(this, getString(R.string.enterAtLeastOneTerm), Toast.LENGTH_LONG).show()
            } else {
                try {
                    date = inputFormat.parse(search_begin_date.text.toString())
                    sDate = outputFormat.format(date)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                try {
                    date = inputFormat.parse(search_end_date.text.toString())
                    eDate = outputFormat.format(date)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                val intent = Intent(view.context, SearchResultActivity::class.java)
                intent.putExtra("SearchQuery", query_include.text?.toString())
                intent.putExtra("start_date", sDate)
                intent.putExtra("end_date", eDate)
                intent.putExtra("section", section)

                view.context.startActivity(intent)
            }
        }

    }

    private fun startActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
