/**
 *
 */
package com.g4g.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.ResourceBundleUtil;

/**
 * @author ankur
 *
 */
public class ChallengeCardSecurity {


	public static void canUserViewThisCard(HttpServletRequest request ,HttpServletResponse response, TwoplayermatchDTO twoplayermatchDTO ) {
			if(DataUtil.getUserDTO(request)!=null) {
				if(twoplayermatchDTO!=null) {
					if(twoplayermatchDTO.getPlayeroneid() ==null || twoplayermatchDTO.getPlayertwoid() ==null)return;
					if(twoplayermatchDTO.getPlayeroneid()!=null && twoplayermatchDTO.getPlayeroneid().getId() == DataUtil.getUserDTO(request).getPlayerDTO().getId()) {
						return;
					}
					if(twoplayermatchDTO.getPlayertwoid()!=null && twoplayermatchDTO.getPlayertwoid().getId() == DataUtil.getUserDTO(request).getPlayerDTO().getId()) {
						return;
					}
					AuditUtil.getInstance().writeSecurityLog(request, response, DataUtil.getCallingMethodWithClassForSecurityTrap(), ResourceBundleUtil.getValue(G4GConstants.SECURITY_USER_INVALID));
				}
			}else {
				AuditUtil.getInstance().writeSecurityLog(request, response, DataUtil.getCallingMethodWithClassForSecurityTrap(), ResourceBundleUtil.getValue(G4GConstants.SECURITY_USER_NOTLOGIN));
			}
	}
}