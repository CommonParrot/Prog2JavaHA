package de.tuberlin.snet.prog2.ue10.consoles

import java.util.Date
import scala.collection.mutable.MutableList
import de.tuberlin.snet.prog2.ue10.consoles.GamerService.GameHolder

object GamerService {

  def main(args: Array[String]): Unit = {
  
    println("Jetzt gehts")
    
    val ps = new Console("Sony", "PlayStation 5", new Date(2017, 8, 23), None, List(new BlueRay), UHD);

    val atari = new Console("Atari", "2600", new Date(1977, 10, 1), None, List(new Cartridge), HD);

    val switch = new Console("Nintendo", "Switch", new Date(2017, 2, 5), Option.apply("5GHz"), List(new Cartridge), FHD);

    println(switch);

    println(atari);

    println(ps);

    println(List(ps, atari, switch).sortWith((x, y) => x.disp.pixel > y.disp.pixel));

    println();  
    
    GameHolder.Game("BattleField 0", "EA", MutableList(ps, atari))
    GameHolder.Game("BattleField 0", "EA", MutableList(ps,switch))
    
    GameHolder.Game("BattleField 0", "Ubisoft", MutableList(switch))
    GameHolder.Game("BattleField 0", "Bethesda", MutableList(switch))
    GameHolder.Game.gamesList.foreach(x=> println(x))
    

//    println(new GameHolder.Game("BattleField 0", "Crytec", MutableList(switch)))

  }

  class Console(var hersteller: String, var model: String, var released: Date, var wifi: Option[String], var formats: List[MediaFormat], var disp: Resolution) {

    override def toString = model + " von " + hersteller + " löst auf in " + disp + " und erkennt Spiele auf " + formats;

  }

  class Resolution(x: Int, y: Int, n: String) {
    var width: Int = x;
    var height: Int = y;
    var name: String = n;

    var pixel: Int = width * height;

    override def toString = name;
  }

  val HD = new Resolution(1280, 720, "HD");

  val FHD = new Resolution(1980, 1280, "FHD");

  val QHD = new Resolution(2560, 1440, "QHD");

  val UHD = new Resolution(3840, 2160, "UHD");

  class MediaFormat(val name: String) {
    override def toString = name;
  }

  class Cartridge(name: String = "Cartridge") extends MediaFormat(name) {
  }

  class DVD(name: String = "DVD") extends MediaFormat(name) {
  }

  class BlueRay(name: String = "BlueRay") extends MediaFormat(name) {
  }

  object GameHolder {

    class Game private(private val name: String, private val hersteller: String, private val consoles: MutableList[Console]) {

      def isSupported(c: Console): Boolean = {
        return consoles.contains(c);
      }

      override def toString = name + " vom Publisher " + hersteller + " jetzt erhältlich für " + consoles;
    }

    object Game{

      val gamesList: MutableList[Game] = MutableList();

      def apply(name: String, hersteller: String, consoles: MutableList[Console]) = {

        if (gamesList.toStream.exists(x => x.name == name && x.hersteller == hersteller)) {

          gamesList.toStream.withFilter(x => x.name == name && x.hersteller == hersteller).foreach(x => x.consoles.++=((consoles).distinct))

        } else {

          var newGame: Game = new Game(name, hersteller, consoles);
          gamesList.+=:(newGame);
          newGame;

        }
     }
    
    }
  }

}