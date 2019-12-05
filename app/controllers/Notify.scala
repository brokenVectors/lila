package controllers

import lila.app._
import lila.notify.Notification.Notifies

import play.api.libs.json._

final class Notify(env: Env) extends LilaController(env) {

  import env.notify.jsonHandlers._

  def recent(page: Int) = Auth { implicit ctx => me =>
    XhrOrRedirectHome {
      val notifies = Notifies(me.id)
      env.notify.api.getNotificationsAndCount(notifies, page) map { res =>
        Ok(Json toJson res) as JSON
      }
    }
  }
}
