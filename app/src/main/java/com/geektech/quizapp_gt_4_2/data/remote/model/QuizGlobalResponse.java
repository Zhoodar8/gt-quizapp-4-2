package com.geektech.quizapp_gt_4_2.data.remote.model;


import com.geektech.quizapp_gt_4_2.model.Global;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class QuizGlobalResponse {
    @SerializedName("overall")
    private Global global;
    @SerializedName("categories")
    private Map<String, Global> globalMap;

    public QuizGlobalResponse(Global global, Map<String, Global> globalMap) {
        this.global = global;
        this.globalMap = globalMap;
    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public Map<String, Global> getGlobalMap() {
        return globalMap;
    }

    public void setGlobalMap(Map<String, Global> globalMap) {
        this.globalMap = globalMap;
    }
}
