import doodle.backend.StandardInterpreter._
import doodle.core.Image._
import doodle.core._
import doodle.jvm.Java2DCanvas._
import doodle.syntax._
import doodle.random._

/*
Chapter 8
 */


//Coding of some examples from chapter 8
object dotExample {

 val dot = Image.circle(5).lineWidth(3).lineColor(Color.crimson)

  val squareDots =
    dot.at(0,0).
      on(dot.at(0,100)).
      on(dot.at(100,100)).
      on(dot.at(100,0))

  def main(args: Array[String]) = {
    squareDots.draw
  }
}


object geometryExample {

  def parametricCircle(angle: Angle): Point = Point.polar(200,angle)

  def sample(start: Angle, samples: Int): Image = {
    val step = Angle.one / samples
    val dot = triangle(10,10)
    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n => dot.at(parametricCircle(angle).toVec) on loop (n-1)
      }
    }
    loop(samples)
  }

  def main(args: Array[String]) = {
    sample(45.degrees,20).draw

  }
}

object flowerExample {

  def rose(angle: Angle): Point = Point.polar((angle * 7).cos * 200, angle)

  def sample(start: Angle, samples: Int): Image = {
    val step = Angle.one / samples
    val dot = triangle(10,10)
    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n => dot.at(rose(angle).toVec) on loop (n-1)
      }
    }
    loop(samples)
  }

  def main(args: Array[String]) = {
    sample(45.degrees,5).draw

  }
}

//testing the tail recursion example, interesting that it says no
/*object testRecursion {

  @tailrec
  def odd(n: Int): Boolean =
  n match {
    case 1 => true
    case n => even(n-1)
  }

  @tailrec
  def even(n: Int): Boolean =
  n match {
    case 1 => false
    case n => odd(n-1)
  }
}*/

//Functions as objects
/*
1. roseFn: takes angle returns point
 */

object roseFnObj {

  def roseFn = (angle: Angle) => Point.cartesian((angle * 7).cos * angle.cos, (angle *7).cos * angle.sin)

}

object multiShapeExample {

  def concentricShapes(count: Int, singleShape: Int => Image): Image =
  count match {
    case 0 => Image.empty
    case n => singleShape(n) on concentricShapes(n-1, singleShape)
  }

  def colorCircle(x: Int) = {
    val circle = Image.circle(40 + 5*x)
    val color = Color.blue.spin((x * 40).degrees)
circle lineWidth 10 lineColor color
  }

  def colorSquares(x: Int) = {
    val square = Image.rectangle(40 + 10*x, 40 + 10*x)
    val color = Color.blue.spin((x * 40).degrees)
    square lineWidth 10 lineColor color
  }

  def triangleFade(x: Int) = {
    val triangleToDraw = Image.triangle(100 + x*20, 100 + x*20)
    val color = Color.blue.fadeOut((1-x/10.0).normalized)
    triangleToDraw lineWidth 10 lineColor color
  }

  def main(args: Array[String]) = {
    (concentricShapes(10,colorCircle) beside concentricShapes(10,colorSquares) beside concentricShapes(10,triangleFade)).draw
  }
}


object testReverse {

  def testReverseSeq = Seq(1,2,3).reverse

  def main(args: Array[String]) = {
     println(testReverseSeq)
  }
}

object goingBeyondExercise {

  val randomProducer = new scala.util.Random
  val dot = Image.circle(1).lineWidth(1).lineColor(Color.blue)
  def circleForRandomPoint(x: Int) = Image.circle(x)
  def randomDot(x: Int) = dot.at(randomProducer.nextInt(x),randomProducer.nextInt(x))

  def circleWithRandomDot(x: Int) = randomDot(x) on circleForRandomPoint(x)



  def main(args: Array[String]) = {
    (circleWithRandomDot(50)).draw
  }
}

object goingBeyondExerciseVersion2 {

  val angle: Random[Angle] = Random.int(0,360).map(_.degrees)
  val radius: Random[Double] = Random.double
  val scale: Double => (Point => Point) =
    (r: Double) => (pt: Point) => Point(pt.x * r, pt.y * r)

//Random[Angle] ??? Random[Double] == Random[Point]

  //map: Random[A] map (A => B): Random[B]
  //flatMap: Random[A] flatMap (A => Random[B]): Random[B]

  val circle: Random[Point] =
    angle.flatMap { a =>
      radius.map { r =>
        Point.polar(r,a)
  }: Random[Point]
  }


  def main(args: Array[String]) = {

  }
}
