package cn.edu.hit.coursety.service

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

data class Score(val sid: String, val cid: String, val score: Int)

@Service
class ScoreService(val db: JdbcTemplate) {
    fun findScores(): List<Score> = db.query("select * from scores") { response, _ ->
        Score(response.getString("sid"), response.getString("cid"), response.getInt("score"))
    }
}