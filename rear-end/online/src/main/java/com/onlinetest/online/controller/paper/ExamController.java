package com.onlinetest.online.controller.paper;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.Score;
import com.onlinetest.online.service.ScoreService;
import com.onlinetest.online.util.HttpServletRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author kevinhuang
 * @date 2020-06-26 17:14
 */
@RestController
@RequestMapping("/submit")
@CrossOrigin(origins = "*")
@Slf4j
public class ExamController {
    @Autowired
    private ScoreService scoreService;
    /**
     * 1.提交成绩单
     * @param userId1
     * @return
     */
    @PostMapping("/addscore")
    public Result addScoreItem(@RequestBody Score score) throws Exception {


        log.info("addscore, userId : " + score);
        if(score.getUserId() == null || score.getPageId() == null || score.getTotalScore() == null
        || score.getTotalScore() < 0)
            return Result.buildError("userId或总分为空!", -1);


        return scoreService.addScoreItem(score);
    }

    /**
     * 2.修改成绩
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/updatescore")
    public Result updateScoreItem(HttpServletRequest request) throws Exception {
        Score score = getScoreFromRequest(request);
        log.info("updatescore, userId : " + score.getUserId());

        return scoreService.updateScore(score);
    }

    /**
     * 3.学生查看成绩
     * @param request
     * @return
     */
    @GetMapping("/lookupscore")
    public Result lookupScore(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(userId == null)
            return Result.buildError("用户信息为空!", -1);
        return scoreService.getScoreByUserId(userId);
    }

    /**
     * 4.获取某张试卷的下的所有的学生成绩
     * @param request
     * @return
     */
    @PostMapping("/getAllScore")
    public Result getAllScore(HttpServletRequest request){
        Integer pageId = HttpServletRequestUtil.getInt(request, "pageId");
        if(pageId == null)
            return Result.buildError("不存在", -1);
        return scoreService.getScoreList(pageId);
    }

    /**
     * 5.获取所有学生成绩
     *
     * @param request
     * @return
     */
    @GetMapping("/getAllScores")
    public Result getAllScores(HttpServletRequest request) {
        log.info("get all scores...");
        return scoreService.getAllScore();
    }


    /**
     * 从请求中获取Score实例
     * @param request
     * @return
     */
    private Score getScoreFromRequest(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer pageId = HttpServletRequestUtil.getInt(request, "pageId");
        Double totalScore = HttpServletRequestUtil.getDouble(request, "totalScore");
        Integer status = HttpServletRequestUtil.getInt(request, "status");


        Score score = new Score();
        score.setPageId(pageId);
        score.setUserId(userId);
        if(totalScore >= 0 && totalScore != null)
            score.setTotalScore(totalScore);
        if(status != null && status != -1)
            score.setStatus(status);
        log.info("addscore : " + score);
        return score;
    }
}
