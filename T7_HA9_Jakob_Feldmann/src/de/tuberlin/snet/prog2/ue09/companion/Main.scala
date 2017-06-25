package de.tuberlin.snet.prog2.ue09.companion

import java.time.LocalDate
import java.time.Month
import du.tuberlin.snet.prog2.ue09.companion.Age

object Main extends App {
  val citty = new Age(LocalDate.of(1999, Month.JANUARY, 31))
  println(Age.ageString(citty))
//  val helloCitty = Age()
//  println(Age.ageString(helloCitty))
}