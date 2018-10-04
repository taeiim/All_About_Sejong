package com.god.parktaeim.all_about_sejong.api.model

data class BicycleStation(val dong : String,
                          val gu : String,
                          val kioskState : Boolean,
                          val lat : Long,
                          val lon : Long,
                          val lockon : Int,
                          val pct : Int,
                          val stationName : String,
                          val stationNo : Int,
                          val totCnt : Int )