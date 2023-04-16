package cn.edu.hit.coursety.service

import cn.edu.hit.coursety.dao.CourseDao
import cn.edu.hit.coursety.dao.ScoreDao
import cn.edu.hit.coursety.dao.UserDao
import org.springframework.stereotype.Service
import cn.edu.hit.coursety.entity.domain.Score
import cn.edu.hit.coursety.entity.vo.StudentCourseScoreVo

@Service
class ScoreService(val scoreDao: ScoreDao, val courseDao: CourseDao, val userDao: UserDao) {
    fun getStudentScores(sid: String): List<StudentCourseScoreVo> {
        val studentScores = scoreDao.getAllScoreBySid(sid)
        val res: List<StudentCourseScoreVo> = studentScores.fold(ArrayList()) { acc, score ->
            val course = courseDao.getCourseByCid(score.cid)
            if (course != null) {
                val teacherUser = userDao.getUserById(course.tid)
                val courseStudentCount = scoreDao.getAllScoreByCid(score.cid).size
                acc.add(StudentCourseScoreVo(score.cid, course.cname, teacherUser?.name
                        ?: course.tid, courseStudentCount, score.score))
            }
            acc
        }
        return res
    }
}