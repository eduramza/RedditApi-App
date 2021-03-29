package com.eduramza.redditapp

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TestDates {

    @Test
    fun testDates(){
        val date = Date(1616967717.0.toLong() * 1000)
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        //val sUnitDate = sdf.format(date)
        val now = System.currentTimeMillis()
        val diff = now - date.time

        val seconds = TimeUnit.MILLISECONDS.toSeconds(diff)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
        val hours = TimeUnit.MILLISECONDS.toHours(diff)
        val days = TimeUnit.MILLISECONDS.toDays(diff)
    }

    @Test
    fun getYear(){
        val yearDays = 365
        val result = 814
        val yearsAgo = result / yearDays

        assertEquals(2, yearsAgo)
    }

    @Test
    fun monthsAgo(){
        val monthsDays = 30
        val result = 92
        val monthsAgo = result / monthsDays

        assertEquals(3, monthsAgo)
    }
}