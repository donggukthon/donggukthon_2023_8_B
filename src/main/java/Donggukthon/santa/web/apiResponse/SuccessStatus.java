package Donggukthon.santa.web.apiResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum SuccessStatus {

    /** /main **/


    /** /user **/
    JOIN_USER(201, "SUCCESS", "회원가입이 완료되었습니다."),
    LOGIN_USER(200, "SUCCESS", "로그인이 완료되었습니다."),
    USER_INFO(200, "SUCCESS", "유저 정보를 반환했습니다."),

    /** /certification **/
    READ_CERTIFICATION(200, "SUCCESS", "후원증명서를 조회했습니다."),

    /** /tree **/
    READ_TREE_DECORATION(200, "SUCCESS", "트리의 데코레이션들이 조회되었습니다."),
    READ_MEMBER_METADATA(200, "SUCCESS", "메타데이터를 포함 유저의 정보를 반환했습니다."),

    /** /decoration **/
    UPDATE_DECORATION(200, "SUCCESS", "유저의 데코레이션이 업데이트 되었습니다."),

    /** /submission **/
    SUBMIT_DONATE(200, "SUCCESS", "후원 신청을 완료했습니다."),

    /** /hall **/
    READ_PEOPLE_WHO_DONATE(200, "SUCCESS", "명예의 전당 유저들이 조회되었습니다.");

    private final int code;
    private final String status;
    private final String message;
}
