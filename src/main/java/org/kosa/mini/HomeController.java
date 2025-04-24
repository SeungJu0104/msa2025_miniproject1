package org.kosa.mini;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	�������� �ڹ� ��ũ��Ʈ �� �ִ� �뵵
//	@Autowired
//	@Qualifier("appPath")
//	private Properties appPath;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
}
