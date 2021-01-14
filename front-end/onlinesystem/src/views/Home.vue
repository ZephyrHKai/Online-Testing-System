<template>
  <div class="home">
    <div class="left-wrap">
      <el-card class="box-card">
        <div slot="header" class="clearfix header">
          <span>单选题</span>
          <el-button type="primary" round>共{{list.length}}题，合计100分</el-button>
        </div>
        <el-card
          :id="'que' + (index + 1)"
          class="box-card list-item"
          shadow="never"
          v-for="(item, index) in list"
          :key="index"
        >
          <div slot="header" class="clearfix">
            <span>{{(index + 1)}}、{{item.scontent}} ({{100/list.length}}分)</span>
          </div>
          <div v-if="item.sa">
            <el-radio v-model="answers[index]" label="sa">
              <span>A.{{item.sa}}</span>
            </el-radio>
          </div>
          <div v-if="item.sb">
            <el-radio v-model="answers[index]" label="sb">
              <span>B.{{item.sb}}</span>
            </el-radio>
          </div>
          <div v-if="item.sc">
            <el-radio v-model="answers[index]" label="sc">
              <span>C.{{item.sc}}</span>
            </el-radio>
          </div>
          <div v-if="item.sd">
            <el-radio v-model="answers[index]" label="sd">
              <span>D.{{item.sd}}</span>
            </el-radio>
          </div>
        </el-card>
      </el-card>
    </div>
    <div class="right-wrap">
      <el-card class="box-card">
        <div slot="header" class="clearfix header">
          <div>答题卡</div>
          <div>{{coundowmTime}}</div>
        </div>
        <a :href="'#que' + o" v-for="o in list.length" :key="o" class="item-index">{{ o }}</a>
        <el-button class="submit-btn" type="primary" @click="submit">交卷</el-button>
      </el-card>
    </div>
  </div>
</template>

<script>
import { getItemListById, submitScore } from "@/utils/api.js";

export default {
  name: "Home",
  data() {
    return {
      list: [],
      answers: [],
      coundowmTime: "00:00:00",
      desTimes: '',
      pageId: '',
      userId: '',
      testTime: 20 * 60 //秒
    };
  },
  created() {
    let { pageId, userId } = this.$route.query;
    this.pageId = +pageId;
    this.userId = +userId;
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      let params = {
        pageId: this.pageId,
      };
      getItemListById(params)
        .then(res => {
          if (res.success) {
            this.list = res.data;
            this.desTimes = new Date().getTime() + this.testTime * 1000;
            this.countDown();
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch(err => {
          this.$message.error("error");
        });
    },
    submit() {
      let totalScore = 0,
        sigalScore = 100 / this.list.length;
      this.answers.forEach((item, index) => {
        if ( item && item.replace("s", "").toUpperCase() == this.list[index].ans ) {
          totalScore += sigalScore * 1;
        }
      })
      let params = {
        userId: this.userId,
        pageId: this.pageId,
        totalScore: totalScore.toFixed(2)
      };
      submitScore(params)
        .then(res => {
          console.log(res);
          if (res.success) {
            this.$alert("您的考试成绩是" + totalScore + "分", "提示", {
              confirmButtonText: "确定",
              callback: action => {
                window.location.href = 'http://tk6tn945wo.54http.tech/onlinetest/stexam';
              }
            });
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    addZero(i) {
      return i < 10 ? "0" + i: i + "";
    },
    countDown() {
      var nowtime = new Date();
      // var endtime = new Date(nowtime.getTime() + 3 * 60 *1000);
      var lefttime = parseInt((this.desTimes - nowtime.getTime()) / 1000);
      var d = parseInt(lefttime / (24 * 60 * 60));
      var h = parseInt((lefttime / (60 * 60)) % 24);
      var m = parseInt((lefttime / 60) % 60);
      var s = parseInt(lefttime % 60);
      d = this.addZero(d);
      h = this.addZero(h);
      m = this.addZero(m);
      s = this.addZero(s);
      this.coundowmTime = `${h}:${m}:${s}`;
      if (lefttime <= 0) {
        //时间到了
        this.submit();
        return;
      }
      setTimeout(this.countDown, 1000);
    }
  }
};
</script>

<style lang="scss" scoped>
.home {
  width: 1000px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  position: relative;
}
.left-wrap {
  flex: 1;
  margin-right: 20px;
  .header {
    .el-button {
      margin-left: 20px;
    }
  }
  .list-item {
    margin-bottom: 20px;
  }
}
.right-wrap {
  width: 300px;
  position: fixed;
  right: 0;
  .el-card ::v-deep .el-card__header {
    padding: 0;
    height: 76px;
  }
  .header {
    display: flex;
    height: 100%;
    & > div {
      width: 50%;
      text-align: center;
      line-height: 76px;
    }
    & > div:first-child {
      background-color: #409eff;
      color: #ffffff;
    }
    & > div:last-child {
      color: red;
    }
  }
}
.item-index {
  border: 1px solid #eeeeee;
  display: inline-block;
  width: 30px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  margin-right: 10px;
  cursor: pointer;
  text-decoration: none;
}
.submit-btn {
  width: 100%;
  margin-top: 20px;
}
</style>
