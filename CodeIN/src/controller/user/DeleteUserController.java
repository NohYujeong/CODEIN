package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.UserManager;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("userId");
    	log.debug("Delete User : {}", deleteId);

		UserManager manager = UserManager.getInstance();
		manager.remove(deleteId);			
		
		String curUserId = UserSessionUtils.getUserFromSession(request.getSession());		
		if (deleteId.equals(curUserId))	{	// 현재 로그인한 사용자를 삭제한 경우
			return "redirect:/user/logout";		// logout 실행
		}
		return "redirect:/user/list";		// 사용자 리스트 요청으로 이동
    }
}
