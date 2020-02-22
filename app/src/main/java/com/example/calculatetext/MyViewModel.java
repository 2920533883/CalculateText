package com.example.calculatetext;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;
import java.util.Random;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> ans;
    private String remark;
    private Random random = new Random(47);
    private int key = 4;
    public int score = 0;
    private int anum = random.nextInt(100)+1;
    private int bnum = random.nextInt(100)+1;
    void init(){
        key = 4;
        anum = random.nextInt(100)+1;
        bnum = random.nextInt(100)+1;
        ans = new MutableLiveData<>();
        ans.setValue("");
        remark = "";
    }
    public MutableLiveData<String> getQuestion() {
        MutableLiveData<String> question = new MutableLiveData<>();
        if (anum > bnum) {
            if (anum % bnum == 0)
            {
                key = 1;
                question.setValue(anum + " ÷ " + bnum + " =");
            }
            else
            {
                key = 2;
                question.setValue(anum + " - " + bnum + " =");
            }
        }
        else
        {
            if (anum % 2 == 0  && anum + bnum < 60)
            {
                key = 3;
                question.setValue(anum + " × " + bnum + " =");
            }
            else
            {
                key = 4;
                question.setValue(anum + " + " + bnum + " =");
            }
        }
        return question;
    }

    public MutableLiveData<String> getAns() {
        if (ans == null){
            ans = new MutableLiveData<>();
            ans.setValue("");
        }
        return ans;
    }

    public String getRemake() {
        if (score < 5) remark = "才"+score+"分，不太行哦。";
        else if (score < 15) remark = "嗯～, 答对了"+score+"个, 不错哦。";
        else remark = "太棒了吧你,帅帅帅！";
        return remark;
    }

    public void add(int n) {
        ans.setValue(ans.getValue()+n);
    }

    int commit(){
        if (Objects.requireNonNull(ans.getValue()).isEmpty()) return -1;
        else
        {
            int res = 0;
            switch (key)
            {
                case 1: res = anum / bnum; break;
                case 2: res = anum - bnum; break;
                case 3: res = anum * bnum; break;
                case 4: res = anum + bnum; break;
            }
            if (Integer.parseInt(ans.getValue()) == res) {
                score++;
                return 1;
            }
            return 0;
        }
    }

    public void reset(){
        ans.setValue("");
    }

}
