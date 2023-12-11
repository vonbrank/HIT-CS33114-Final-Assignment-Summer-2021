package cn.edu.hit.coursety.dao

import cn.edu.hit.coursety.entity.domain.Score
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class ScoreDao(val db: JdbcTemplate) {
    fun getAllScoreBySid(sid: String): List<Score> = db.query("SELECT * FROM scores WHERE sid=?", sid) { response, _ ->
        Score(response.getString("sid"), response.getString("cid"), response.getInt("score"))
    }

    fun getAllScoreByCid(cid: String): List<Score> = db.query("SELECT * FROM scores WHERE cid=?", cid) { response, _ ->
        Score(response.getString("sid"), response.getString("cid"), response.getInt("score"))
    }
}