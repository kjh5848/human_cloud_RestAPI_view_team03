package com.example.project_v2.apply;

import com.example.project_v2.notice.Notice;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.user.User;
import lombok.Data;

public class ApplyResponse {

    @Data
    public static class ApplyDTO{

        private Integer id; // 지원 번호
        private Integer noticeId; // 공고 번호
        private Integer resumeId; // 이력서 번호
        private Integer noticeUserId; // 공고 유저의 번호
        private Integer resumeUserId; // 이력서의 유저의 번호
        private String name; // 공고 올린 회사 이름
        private String title; // 공고 타이틀
        private String deadline; // 마감일
        private String type; // 고용 형태
        private Boolean isApplyOwner; // 지원의 주인 여부

        public ApplyDTO(Apply apply, Notice notice, Resume resume , User sessionUser) {
            this.id = apply.getId();
            this.noticeId = notice.getId();
            this.resumeId = resume.getId();
            this.noticeUserId = notice.getUser().getId();
            this.resumeUserId = resume.getUser().getId();
            this.name = resume.getUser().getName();
            this.title = resume.getTitle();
            this.deadline = notice.getDeadline();
            this.type = notice.getType();
            this.isApplyOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == resumeUserId) {
                    isApplyOwner = true;
                }
            }

        }
    }

    @Data
    public static class SelectResumeDTO{
        private Integer id;
        private Integer resumeId;
        private Boolean isResumeOwner;

        public SelectResumeDTO(Apply apply, User sessionUser) {
            this.id = apply.getId();
            this.isResumeOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == resumeId) {
                    isResumeOwner = true;
                }
            }
        }
    }

}
