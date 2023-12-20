package Donggukthon.santa.web.apiResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorStatus {

    /** /Token **/
    INVALID_TOKEN(400, "FAIL", "유효하지 않은 토큰 값입니다."),

    /** /main **/
    READ_TREE_DECORATION(400, "FAIL", "트리의 데코레이션들 조회를 실패했습니다."),

    /** /user **/
    HAVE_USER(500, "FAIL", "이미 존재하는 이매일입니다."),
    JOIN_USER(400, "FAIL", "회원가입에 실패하였습니다."),
    LOGIN_USER(401, "FAIL", "비밀번호가 일치하지 않습니다."),
    CANNOT_FIND_EMAIL(400, "FAIL", "이메일을 찾을 수 없습니다."),
    USER_INFO(400, "FAIL", "유저 정보 반환 실패"),

    /** /certification **/
    NON_DONATE(400, "FAIL", "아직 기부하지 않은 멤버입니다.");

    /** /submission **/

    /** /hall **/

    private final int code;
    private final String status;
    private final String message;

}
