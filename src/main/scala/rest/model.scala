package rest

/**
 * @author sjalipar
 */
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import spray.json._

case class response(message: String, time : DateTime)

object restJsonProtocol extends DefaultJsonProtocol {
  
  implicit object dateTimeFormat extends RootJsonFormat[DateTime] {

    override def read(json: JsValue): DateTime = json match {
      case JsString(dateTime) => ISODateTimeFormat.dateTime().withZoneUTC().parseDateTime(dateTime)
      case _                     => throw new DeserializationException("Date and Time String Expected")
    }
    override def write(dateTime: DateTime): JsValue =
      JsString(ISODateTimeFormat.dateTime().withZoneUTC().print(dateTime))
  }
  
  implicit val responseJsonProtocol = jsonFormat2(response)
}