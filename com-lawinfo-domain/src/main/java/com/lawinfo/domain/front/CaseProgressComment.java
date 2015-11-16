package com.lawinfo.domain.front;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;

/**
 * Created by wangrongtao on 15/11/3.
 */
public class CaseProgressComment extends BaseDomain implements Comparable<CaseProgressComment>{
    private long id;
    private long caseinfoid;
    private int processnodeid;
    private String comment;
    private String nexttask;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCaseinfoid() {
        return caseinfoid;
    }

    public void setCaseinfoid(long caseinfoid) {
        this.caseinfoid = caseinfoid;
    }

    public int getProcessnodeid() {
        return processnodeid;
    }

    public void setProcessnodeid(int processnodeid) {
        this.processnodeid = processnodeid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNexttask() {
        return nexttask;
    }

    public void setNexttask(String nexttask) {
        this.nexttask = nexttask;
    }


    @Override
    public int compareTo(CaseProgressComment o) {
        if (o!=null) {
            long diff = this.createtime-o.getCreatetime();
            if (diff == 0) {
                return 0;
            }else if (diff >0) {
                return 1;
            }else if (diff < 0) {
                return -1;
            }
        }
        return 0;
    }
}
