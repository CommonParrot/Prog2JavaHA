package du.tuberlin.snet.prog2.ue09.companion

import java.time.LocalDate
import java.time.Period
import java.time.Month

class Age(private val birthday: LocalDate)

object Age {
  def age(date: Age): Period = {
    val today = LocalDate.now()
    Period.between(date.birthday, today)
  }
  
  def ageString(date: Age): String = {
    val tmpAge: Period = age(date)
    s"You are ${tmpAge.getYears()} years, ${tmpAge.getMonths()} months, and ${tmpAge.getDays()} days old."
  }
}