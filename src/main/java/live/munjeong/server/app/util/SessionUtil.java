package live.munjeong.server.app.util;

import live.munjeong.server.app.cons.Constant;
import live.munjeong.server.app.user.LoginVO;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    /**
     * Spring RequestContextHolder에 있는 Session 가져오기 (create = false)
     * @return HttpSession 현재 request의 session (요청이 없으면 null)
     */
    private static HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) return null;
        return requestAttributes.getRequest().getSession(false);
    }

    /**
     * 세션에서 String 값을 가져온다.
     * @param key 세션에서 가져올 키
     * @param defaultValue 세션이 없을 때 가져올 기본 값
     */
    public static String getStringAttribute(String key, String defaultValue) {
        HttpSession session = getSession();
        if ( session == null ) return defaultValue;
        return (String) session.getAttribute(key);
    }

    /**
     * 세션에서 String 값을 가져온다.
     * @param key 세션에서 가져올 키
     */
    public static String getStringAttribute(String key) {
        return getStringAttribute(key, null);
    }


    /**
     * 세션에서 값을 가져온다.
     * @param key 세션에서 가져올 키
     * @param defaultValue 세션이 null일 때 가져올 기본 값
     */
    public static Object getAttribute(String key, Object defaultValue) {
        HttpSession session = getSession();
        if ( session == null ) return defaultValue;
        return session.getAttribute(key);
    }

    /**
     * 세션에서 값을 가져온다.
     * @param key 세션에서 가져올 키
     */
    public static Object getAttribute(String key) {
        return getAttribute(key, null);
    }

    /**
     * 세션에 값을 설정한다.
     * @param key 세션의 key
     * @param object
     * @throws HttpSessionRequiredException 세션이 없을 경우 에러 발생한다.
     */
    public static void setAttribute(String key, Object object) throws HttpSessionRequiredException {
        HttpSession session = getSession();
        if( session == null ) {
            throw new HttpSessionRequiredException("세션 연결이 종료되었습니다.");
        }
        session.setAttribute(key, object);
    }

    /**
     * 세션에서 값을 지운다. 세션이 없을 경우 아무것도 지우지 않는다.
     * @param key
     */
    public static void removeAttribute(String key)  {
        HttpSession session = getSession();
        if( session != null ) {
            session.removeAttribute(key);
        }
    }

    /**
     * 세션에서 사용자 이름을 가져온다. (없으면 null)
     * @return String 사용자 이름
     */
    public static String getUserNm() {
        LoginVO loginVO = getLoginVO();
        if(loginVO == null) return null;
        return loginVO.getUserNm();
    }

    /**
     * 세션에서 LoginVO를 가져온다. (없으면 null)
     * @return LoginVO
     */
    private static LoginVO getLoginVO()  {
        return (LoginVO) getAttribute(Constant.LOGIN_VO);
    }
}
